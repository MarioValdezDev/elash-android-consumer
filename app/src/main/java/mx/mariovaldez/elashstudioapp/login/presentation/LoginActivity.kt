package mx.mariovaldez.elashstudioapp.login.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.os.Bundle
import android.os.Looper
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.Priority
import dagger.hilt.android.AndroidEntryPoint
import mx.mariovaldez.elashstudioapp.app.domain.models.AppPermission.AccessFineLocation
import mx.mariovaldez.elashstudioapp.databinding.ActivityLoginBinding
import mx.mariovaldez.elashstudioapp.home.presentation.HomeActivity
import mx.mariovaldez.elashstudioapp.ktx.clearAnimationTransition
import mx.mariovaldez.elashstudioapp.ktx.exhaustive
import mx.mariovaldez.elashstudioapp.ktx.gone
import mx.mariovaldez.elashstudioapp.ktx.isAppPermissionGranted
import mx.mariovaldez.elashstudioapp.ktx.launchAppPermissionRequest
import mx.mariovaldez.elashstudioapp.ktx.observe
import mx.mariovaldez.elashstudioapp.ktx.registerActionsForAppPermissionRequest
import mx.mariovaldez.elashstudioapp.ktx.viewBinding
import mx.mariovaldez.elashstudioapp.ktx.visible
import timber.log.Timber

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModels()
    private val binding: ActivityLoginBinding by viewBinding(
        ActivityLoginBinding::inflate
    )

    private lateinit var appPermissionActivityResultLauncher: ActivityResultLauncher<String>

    private var requestingLocationUpdates: Boolean = false
    private var fusedLocationClient: FusedLocationProviderClient? = null
    private var resolutionForResult: ActivityResultLauncher<IntentSenderRequest>? = null
    private var locationRequest: LocationRequest? = null
    private var locationCallback: LocationCallback? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        updateValuesFromBundle(savedInstanceState)
        setupLocationService()
        appPermissionActivityResultLauncher = registerActionsForAppPermissionRequest(
            this::onLocationPermissionGranted,
            this::onLocationPermissionDenied
        )
        setupObservers()
        setupListeners()
    }

    override fun onResume() {
        super.onResume()
        if (requestingLocationUpdates) startLocationUpdates()
    }

    override fun onPause() {
        super.onPause()
        stopLocationUpdates()
    }

    private fun setupObservers() {
        viewModel.state.observe(this, ::handle)
        viewModel.event.observe(this, ::handle)
        viewModel.isSignInButtonEnabled.observe(this, ::setupEnableSignInButton)
    }

    private fun setupEnableSignInButton(isEnabled: Boolean) =
        binding.signInButton.apply {
            this.isEnabled = isEnabled
            println(isEnabled)
        }

    private fun setupListeners() {
        binding.apply {
            emailTextInputEditText.addTextChangedListener {
                validateCredentials()
            }
            passwordTextInputEditText.addTextChangedListener {
                validateCredentials()
            }
            signInButton.setOnClickListener {
                viewModel.validateUserLocation(isAppPermissionGranted(AccessFineLocation))
            }
        }
    }

    private fun validateCredentials() {
        with(binding) {
            println(emailTextInputEditText.text.toString().trim())
            println(passwordTextInputEditText.text.toString().trim())
            viewModel.validateCredentials(
                emailTextInputEditText.text.toString().trim(),
                passwordTextInputEditText.text.toString().trim()
            )
        }
    }

    private fun showProgress() {
        with(binding) {
            progressBar.visible()
            signInButton.gone()
        }
    }

    private fun hideProgress() {
        with(binding) {
            progressBar.gone()
            signInButton.visible()
        }
    }

    private fun handle(state: LoginViewModel.State?) {
        when (state) {
            LoginViewModel.State.Loading -> showProgress()
            LoginViewModel.State.Success -> {
                hideProgress()
                HomeActivity.launch(this)
                clearAnimationTransition()
                finish()
            }

            else -> Unit
        }.exhaustive
    }

    private fun handle(event: LoginViewModel.Event) {
        when (event) {
            LoginViewModel.Event.RequestUserLocationAppPermission -> launchAppPermissionRequest(
                appPermissionActivityResultLauncher,
                AccessFineLocation
            )

            is LoginViewModel.Event.ShowError -> {
                // showError(event.message)}
            }

            LoginViewModel.Event.StartGettingUserLocation -> onLocationPermissionGranted()
            LoginViewModel.Event.ShowUserOrPasswordIncorrect -> {
                // do something
            }
        }.exhaustive
    }

    private fun updateValuesFromBundle(savedInstanceState: Bundle?) {
        savedInstanceState ?: return
        if (savedInstanceState.keySet().contains(REQUESTING_LOCATION_UPDATES_KEY)) {
            requestingLocationUpdates =
                savedInstanceState.getBoolean(REQUESTING_LOCATION_UPDATES_KEY)
        }
    }

    private fun setupLocationService() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        resolutionForResult = registerForActivityResult(
            ActivityResultContracts.StartIntentSenderForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                onLocationPermissionGranted()
            } else {
                onLocationPermissionDenied()
            }
        }
    }

    @SuppressLint("MissingPermission")
    fun startLocationUpdates() {
        if (
            isAppPermissionGranted(AccessFineLocation) &&
            locationRequest != null &&
            locationCallback != null
        ) {
            fusedLocationClient?.requestLocationUpdates(
                locationRequest!!,
                locationCallback!!,
                Looper.getMainLooper()
            )
        }
    }

    private fun stopLocationUpdates() {
        requestingLocationUpdates = false
        locationCallback?.let { fusedLocationClient?.removeLocationUpdates(it) }
    }

    @SuppressLint("MissingPermission")
    private fun onLocationPermissionGranted() {
        with(binding) {
            viewModel.login(
                emailTextInputEditText.text.toString(),
                passwordTextInputEditText.text.toString()
            )
        }
        fusedLocationClient?.lastLocation
            ?.addOnSuccessListener { location ->
                if (location != null) {
                    with(binding) {
                        viewModel.login(
                            emailTextInputEditText.text.toString(),
                            passwordTextInputEditText.text.toString()
                        )
                    }
                } else {
                    requestingLocationUpdates = true
                    createLocationRequest()
                    createLocationCallback()
                }
            }
    }

    private fun createLocationRequest() {
        locationRequest = LocationRequest.Builder(
            Priority.PRIORITY_HIGH_ACCURACY,
            LOCATION_REQUEST_INTERVAL
        ).apply {
            val builder = LocationSettingsRequest.Builder().addLocationRequest(this.build())
            val client = LocationServices.getSettingsClient(this@LoginActivity)
            client.checkLocationSettings(builder.build())
                .addOnSuccessListener {
                    startLocationUpdates()
                }
                .addOnFailureListener { exception ->
                    if (exception is ResolvableApiException) {
                        try {
                            resolutionForResult?.launch(
                                IntentSenderRequest.Builder(exception.resolution).build()
                            )
                        } catch (sendIntentException: IntentSender.SendIntentException) {
                            Timber.e(sendIntentException)
                        }
                    }
                }
        }.build()
       /* locationRequest = LocationRequest.create().apply {
            interval = LOCATION_REQUEST_INTERVAL
            fastestInterval = FASTEST_LOCATION_REQUEST_INTERVAL
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }.apply {
            val builder = LocationSettingsRequest.Builder().addLocationRequest(this)
            val client = LocationServices.getSettingsClient(this@LoginActivity)
            client.checkLocationSettings(builder.build())
                .addOnSuccessListener {
                    startLocationUpdates()
                }
                .addOnFailureListener { exception ->
                    if (exception is ResolvableApiException) {
                        try {
                            resolutionForResult?.launch(
                                IntentSenderRequest.Builder(exception.resolution).build(),
                            )
                        } catch (sendIntentException: IntentSender.SendIntentException) {
                            Timber.e(sendIntentException)
                        }
                    }
                }
        }*/
    }

    private fun createLocationCallback() {
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                locationResult.locations.firstOrNull()?.let { location ->
                    println(location)
                    with(binding) {
                        viewModel.login(
                            emailTextInputEditText.text.toString(),
                            passwordTextInputEditText.text.toString()
                        )
                    }
                }
            }
        }
    }

    private fun onLocationPermissionDenied() {
        hideProgress()
        // Show error dialog when the user denied location permission.
    }

    companion object {
        private const val LOCATION_REQUEST_INTERVAL: Long = 10000
        private const val FASTEST_LOCATION_REQUEST_INTERVAL: Long = 5000
        private const val REQUESTING_LOCATION_UPDATES_KEY: String =
            "REQUESTING_LOCATION_UPDATES_KEY"

        fun launch(from: Context) =
            from.startActivity(Intent(from, LoginActivity::class.java))
    }
}
