package mx.mariovaldez.elashstudioapp.homeEmployee.domain.usecases

import dagger.Reusable
import mx.mariovaldez.elashstudioapp.homeEmployee.presentation.models.HomeEmployeeSection
import javax.inject.Inject

@Reusable
internal class FindHomeEmployeeSections @Inject constructor() {

    operator fun invoke(id: String): HomeEmployeeSection? =
        HomeEmployeeSection.values().find { it.name == id }
}