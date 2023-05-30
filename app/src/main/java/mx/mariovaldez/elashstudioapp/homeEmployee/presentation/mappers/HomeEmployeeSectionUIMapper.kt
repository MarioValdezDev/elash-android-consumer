package mx.mariovaldez.elashstudioapp.homeEmployee.presentation.mappers

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import mx.mariovaldez.elashstudioapp.R
import mx.mariovaldez.elashstudioapp.domain.mapper.Mapper
import mx.mariovaldez.elashstudioapp.home.presentation.models.SectionUI
import mx.mariovaldez.elashstudioapp.homeEmployee.presentation.models.HomeEmployeeSection
import mx.mariovaldez.elashstudioapp.ktx.findDrawable
import javax.inject.Inject

internal class HomeEmployeeSectionUIMapper @Inject constructor(
    @ApplicationContext private val context: Context,
) : Mapper<HomeEmployeeSection, SectionUI> {

    override fun map(value: HomeEmployeeSection): SectionUI = with(value) {
        SectionUI(
            name,
            context.findDrawable(
                when (value) {
                    HomeEmployeeSection.SALE -> R.drawable.icv_sale
                    HomeEmployeeSection.SPENT -> R.drawable.icv_curse
                }
            ),
            context.getString(
                when (value) {
                    HomeEmployeeSection.SALE -> R.string.act_home_emp_sale
                    HomeEmployeeSection.SPENT -> R.string.act_home_emp_spent
                }
            ),
        )
    }
}