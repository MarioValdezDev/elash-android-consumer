package mx.mariovaldez.elashstudioapp.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mx.mariovaldez.elashstudioapp.login.domain.usecases.LoginUseCase
import mx.mariovaldez.elashstudioapp.login.domain.usecases.RegisterDefaultUserUserUseCase
import javax.inject.Inject

@HiltViewModel
internal class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val registerDefaultUserUserUseCase: RegisterDefaultUserUserUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<State?> = MutableStateFlow(null)
    val state: StateFlow<State?> get() = _state

    private val _event: MutableSharedFlow<Event> = MutableSharedFlow()
    val event: SharedFlow<Event> get() = _event

    private val _isSignInButtonEnabled: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isSignInButtonEnabled: StateFlow<Boolean> get() = _isSignInButtonEnabled

    fun validateCredentials(
        username: String,
        password: String
    ) = viewModelScope.launch {
        _isSignInButtonEnabled.value = username.isNotEmpty() && password.isNotEmpty()
    }

    fun validateUserLocation(isUserLocationPermissionGranted: Boolean) = viewModelScope.launch {
        _state.value = State.Loading
        println("$isUserLocationPermissionGranted GRANTED")
        if (isUserLocationPermissionGranted) {
            _event.emit(Event.StartGettingUserLocation)
        } else {
            _event.emit(Event.RequestUserLocationAppPermission)
        }
    }

    fun login(username: String, password: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                _state.value = State.Loading
                loginUseCase(username, password)
            }
                .onSuccess {
                    _state.value = State.Success
                }
                .onFailure {
                    _state.value = State.Error
                }
        }
    }

    init {
        viewModelScope.launch {
            kotlin.runCatching {
                registerDefaultUserUserUseCase()
            }
        }
    }

    sealed class State {
        object Loading : State()
        object Success : State()
        object Error : State()
    }

    sealed class Event {

        data class ShowError(val message: String) : Event()

        object RequestUserLocationAppPermission : Event()

        object ShowUserOrPasswordIncorrect : Event()

        object StartGettingUserLocation : Event()
    }
}
