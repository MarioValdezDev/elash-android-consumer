package mx.mariovaldez.elashstudioapp.home.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import mx.mariovaldez.elashstudioapp.databinding.ActivityHomeBinding
import mx.mariovaldez.elashstudioapp.home.presentation.adapters.SectionsAdapter
import mx.mariovaldez.elashstudioapp.ktx.viewBinding

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private val binding: ActivityHomeBinding by viewBinding (
        ActivityHomeBinding::inflate
    )
    private val viewModel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupSections()
    }

    private fun setupSections(){
        val sectionsAdapter = SectionsAdapter { id ->
            viewModel.sectionClicked(id)
        }.apply {
            addSections(viewModel.getSections())
        }
        with(binding.home) {
            sectionsRecyclerView.apply {
                setHasFixedSize(true)
                adapter = sectionsAdapter
            }
        }
    }

    companion object {

        fun launch(from: Context) = from.startActivity(Intent(from, HomeActivity::class.java))
    }
}
