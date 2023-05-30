package mx.mariovaldez.elashstudioapp.home.domain.usecases

import dagger.Reusable
import mx.mariovaldez.elashstudioapp.home.presentation.models.HomeSection
import javax.inject.Inject

@Reusable
internal class FindHomeSection @Inject constructor() {

    operator fun invoke(id: String): HomeSection? = HomeSection.values().find { it.name == id }
}
