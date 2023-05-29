package mx.mariovaldez.elashstudioapp.home.presentation.mappers


import android.content.Context
import dagger.Reusable
import dagger.hilt.android.qualifiers.ApplicationContext
import mx.mariovaldez.elashstudioapp.R
import mx.mariovaldez.elashstudioapp.domain.mapper.Mapper
import mx.mariovaldez.elashstudioapp.home.presentation.models.HomeSection
import mx.mariovaldez.elashstudioapp.home.presentation.models.SectionUI
import mx.mariovaldez.elashstudioapp.ktx.findDrawable
import javax.inject.Inject

@Reusable
internal class HomeSectionUIMapper @Inject constructor(
    @ApplicationContext private val context: Context,
) : Mapper<HomeSection, SectionUI> {

    override fun map(value: HomeSection): SectionUI = with(value) {
        SectionUI(
            name,
            context.findDrawable(
                when (value) {
                    HomeSection.SALE -> R.drawable.icv_sale
                    HomeSection.CURSES -> R.drawable.icv_curse
                    HomeSection.EMPLOYEES -> R.drawable.icv_employees
                    HomeSection.PRODUCTS -> R.drawable.icv_sale
                }
            ),
            context.getString(
                when (value) {
                    HomeSection.SALE -> R.string.sale_item
                    HomeSection.CURSES -> R.string.curses_item
                    HomeSection.EMPLOYEES -> R.string.employees_item
                    HomeSection.PRODUCTS -> R.string.products_item
                }
            ),
        )
    }
}
