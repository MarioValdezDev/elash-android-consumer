package mx.mariovaldez.elashstudioapp.homeEmployee.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mx.mariovaldez.elashstudioapp.home.presentation.models.SectionUI
import mx.mariovaldez.elashstudioapp.homeEmployee.domain.usecases.FindHomeEmployeeSections
import mx.mariovaldez.elashstudioapp.homeEmployee.domain.usecases.GetHomeEmployeeSections
import mx.mariovaldez.elashstudioapp.homeEmployee.presentation.mappers.HomeEmployeeSectionUIMapper
import mx.mariovaldez.elashstudioapp.homeEmployee.presentation.models.HomeEmployeeSection
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
internal class HomeEmployeeViewModel @Inject constructor(
    private val homeSectionEmployeeSectionUIMapper: HomeEmployeeSectionUIMapper,
    private val getHomeEmployeeSections: GetHomeEmployeeSections,
    private val findHomeEmployeeSections: FindHomeEmployeeSections,
) : ViewModel() {

    private val _state: MutableStateFlow<State> = MutableStateFlow(State.Loading)
    val state: StateFlow<State> get() = _state

    private val _event: MutableSharedFlow<Event> = MutableSharedFlow()
    val event: SharedFlow<Event> get() = _event

    fun findSections(): List<SectionUI> =
        homeSectionEmployeeSectionUIMapper.map(getHomeEmployeeSections())

    fun onSectionClicked(id: String) {
        val homeSection = findHomeEmployeeSections(id)
        viewModelScope.launch {
            _event.emit(homeSection.toEvent())
        }
    }

    private fun HomeEmployeeSection?.toEvent(): Event = when (this) {

        HomeEmployeeSection.SALE -> Event.NavigateToChooseArticle

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

        object NavigateToChooseArticle : Event()

        data class ShowError(val message: String) : Event()
    }
}
