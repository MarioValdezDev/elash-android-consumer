package mx.mariovaldez.elashstudioapp.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mx.mariovaldez.elashstudioapp.home.domain.usecases.FindHomeSection
import mx.mariovaldez.elashstudioapp.home.domain.usecases.GetHomeSections
import mx.mariovaldez.elashstudioapp.home.presentation.mappers.HomeSectionUIMapper
import mx.mariovaldez.elashstudioapp.home.presentation.models.HomeSection
import mx.mariovaldez.elashstudioapp.home.presentation.models.SectionUI
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val homeSectionUIMapper: HomeSectionUIMapper,
    private val getHomeSections: GetHomeSections,
    private val findHomeSection: FindHomeSection,
) : ViewModel() {

    private val _state: MutableStateFlow<State> = MutableStateFlow(State.Loading)
    val state: StateFlow<State> get() = _state

    private val _event: MutableSharedFlow<Event> = MutableSharedFlow()
    val event: SharedFlow<Event> get() = _event


    fun getSections(): List<SectionUI> = homeSectionUIMapper.map(getHomeSections())


    fun sectionClicked(id: String) {
        val homeSection = findHomeSection(id)
        viewModelScope.launch {
            _event.emit(homeSection.toEvent())
        }
    }

    private fun HomeSection?.toEvent(): Event = when (this) {
        HomeSection.SALE -> Event.NavigateToSale
        HomeSection.CURSES -> Event.NavigateToCurses
        HomeSection.EMPLOYEES -> Event.NavigateToEmployees
        else -> {
            Timber.e(
                IllegalArgumentException(
                    "An error occurred when selecting the desired section on the home screen, an " +
                            "unidentified one was selected."
                )
            )
            Event.ShowError("")
        }
    }

    sealed class State {
        object Loading : State()
    }

    sealed class Event {
        object NavigateToSale : Event()
        object NavigateToCurses : Event()
        object NavigateToEmployees : Event()
        data class ShowError(val error: String) : Event()
    }
}
