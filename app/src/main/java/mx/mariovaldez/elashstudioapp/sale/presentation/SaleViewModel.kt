package mx.mariovaldez.elashstudioapp.sale.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SaleViewModel @Inject constructor() : ViewModel() {

    private val _state: MutableStateFlow<State> = MutableStateFlow(State.IncompletedRequirement)
    val state: StateFlow<State> get() = _state

    private val _event: MutableSharedFlow<Event> = MutableSharedFlow()
    val event: SharedFlow<Event> get() = _event

    fun onRequirementCompleted(event: Event) {
        viewModelScope.launch { _event.emit(event) }
    }

    sealed class State {

        object CompletedRequirement : State()

        object IncompletedRequirement : State()

        object ShowError : State()

        object Loading : State()
    }

    sealed class Event {

        abstract val type: EventType

        data class  NavigateToChooseArticle(
            override val type: EventType = EventType.NAVIGATION
        ): Event()
    }

    enum class EventType {

        NAVIGATION,
    }
}
