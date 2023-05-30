package mx.mariovaldez.elashstudioapp.home.domain.usecases

import dagger.Reusable
import mx.mariovaldez.elashstudioapp.home.presentation.models.HomeSection
import javax.inject.Inject

@Reusable
internal class GetHomeSections @Inject constructor() {

    operator fun invoke() : List<HomeSection> = HomeSection.values().toList()
}