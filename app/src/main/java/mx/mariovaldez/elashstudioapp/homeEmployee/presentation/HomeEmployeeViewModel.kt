package mx.mariovaldez.elashstudioapp.homeEmployee.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import mx.mariovaldez.elashstudioapp.home.presentation.models.SectionUI
import mx.mariovaldez.elashstudioapp.homeEmployee.domain.usecases.FindHomeEmployeeSections
import mx.mariovaldez.elashstudioapp.homeEmployee.domain.usecases.GetHomeEmployeeSections
import mx.mariovaldez.elashstudioapp.homeEmployee.presentation.mappers.HomeEmployeeSectionUIMapper
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

    sealed class Event {

    }

    fun findSections(): List<SectionUI> =
        homeSectionEmployeeSectionUIMapper.map(getHomeEmployeeSections())

    sealed class State {

        object Loading : State()
    }
}
