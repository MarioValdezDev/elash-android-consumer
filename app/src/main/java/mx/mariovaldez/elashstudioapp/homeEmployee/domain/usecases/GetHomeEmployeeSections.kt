package mx.mariovaldez.elashstudioapp.homeEmployee.domain.usecases

import dagger.Reusable
import mx.mariovaldez.elashstudioapp.homeEmployee.presentation.models.HomeEmployeeSection
import javax.inject.Inject

@Reusable
internal class GetHomeEmployeeSections @Inject constructor() {

    operator fun invoke(): List<HomeEmployeeSection> = HomeEmployeeSection.values().toList()
}