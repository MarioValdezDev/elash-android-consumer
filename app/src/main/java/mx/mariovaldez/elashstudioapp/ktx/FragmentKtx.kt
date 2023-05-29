package mx.mariovaldez.elashstudioapp.ktx

import android.content.pm.PackageManager
import android.net.Uri
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import mx.mariovaldez.elashstudioapp.app.domain.models.AppPermission
import mx.mariovaldez.elashstudioapp.ui.delegates.FragmentViewBindingDelegate

fun <T : ViewBinding> Fragment.viewBinding(viewBindingFactory: (View) -> T) =
    FragmentViewBindingDelegate(this, viewBindingFactory)

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Fragment.isAppPermissionGranted(permission: AppPermission): Boolean = context?.let {
    ContextCompat.checkSelfPermission(it, permission.name) == PackageManager.PERMISSION_GRANTED
} ?: false

fun Fragment.shouldShowRequestAppPermissionRationale(permission: AppPermission): Boolean =
    shouldShowRequestPermissionRationale(permission.name)

fun Fragment.registerActionsForAppPermissionRequest(
    onGuaranteed: () -> Unit,
    onDenied: () -> Unit
): ActivityResultLauncher<String> = registerForActivityResult(RequestPermission()) { isGranted ->
    if (isGranted) onGuaranteed() else onDenied()
}

fun Fragment.launchAppPermissionRequest(
    requestPermissionLauncher: ActivityResultLauncher<String>,
    permission: AppPermission
) = requestPermissionLauncher.launch(permission.name)

fun Fragment.shareImageFromUri(uri: Uri) =
    (requireActivity() as? AppCompatActivity)?.shareImageFromUri(uri)
