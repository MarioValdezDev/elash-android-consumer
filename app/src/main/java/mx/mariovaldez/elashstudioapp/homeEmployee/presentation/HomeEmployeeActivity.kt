package mx.mariovaldez.elashstudioapp.homeEmployee.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import mx.mariovaldez.elashstudioapp.databinding.ActivityHomeEmployeeBinding
import mx.mariovaldez.elashstudioapp.homeEmployee.presentation.adapters.SectionsEmployeeAdapter
import mx.mariovaldez.elashstudioapp.ktx.observe
import mx.mariovaldez.elashstudioapp.ktx.viewBinding
import mx.mariovaldez.elashstudioapp.sale.presentation.SaleActivity

@AndroidEntryPoint
class HomeEmployeeActivity : AppCompatActivity() {

    private val binding: ActivityHomeEmployeeBinding by viewBinding(
        ActivityHomeEmployeeBinding::inflate,
    )

    private val viewModel: HomeEmployeeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupSections()
        setupObservers()
    }

    private fun setupSections() {
        val sectionsAdapter = SectionsEmployeeAdapter { id ->
            viewModel.onSectionClicked(id)
        }.apply {
            addSections(viewModel.findSections())
        }
        with(binding.home) {
            sectionsRecyclerView.apply {
                setHasFixedSize(true)
                adapter = sectionsAdapter
            }
        }
    }

    private fun setupObservers(){
        viewModel.event.observe(this, ::handle)
    }

    private fun handle(event: HomeEmployeeViewModel.Event){
        when(event){
            is HomeEmployeeViewModel.Event.NavigateToChooseArticle -> SaleActivity.launch(this)
            else -> {

            }
        }

    }

    companion object {

        fun launch(from: Context) =
            from.startActivity(Intent(from, HomeEmployeeActivity::class.java))
    }
}