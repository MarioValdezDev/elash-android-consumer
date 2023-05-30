package mx.mariovaldez.elashstudioapp.homeEmployee.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import mx.mariovaldez.elashstudioapp.databinding.ActivityHomeEmployeeBinding
import mx.mariovaldez.elashstudioapp.homeEmployee.presentation.adapters.SectionsEmployeeAdapter
import mx.mariovaldez.elashstudioapp.ktx.viewBinding

@AndroidEntryPoint
class HomeEmployeeActivity : AppCompatActivity() {

    private val binding: ActivityHomeEmployeeBinding by viewBinding(
        ActivityHomeEmployeeBinding::inflate
    )

    private val viewModel: HomeEmployeeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupSections()
    }

    private fun setupSections() {
        val sectionsAdapter = SectionsEmployeeAdapter { id ->
            println("click section $id")
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

    companion object {

        fun launch(from: Context) =
            from.startActivity(Intent(from, HomeEmployeeActivity::class.java))
    }
}