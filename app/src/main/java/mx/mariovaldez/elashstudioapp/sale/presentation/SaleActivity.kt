package mx.mariovaldez.elashstudioapp.sale.presentation

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import mx.mariovaldez.elashstudioapp.R
import mx.mariovaldez.elashstudioapp.databinding.ActivitySaleBinding
import mx.mariovaldez.elashstudioapp.ktx.observe
import mx.mariovaldez.elashstudioapp.ktx.viewBinding
import mx.mariovaldez.elashstudioapp.sale.presentation.chooseArticle.ChooseProductFragment

@AndroidEntryPoint
class SaleActivity : AppCompatActivity() {

    private val binding: ActivitySaleBinding by viewBinding(
        ActivitySaleBinding::inflate,
    )
    private val viewModel: SaleViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initViews()
        initObservers()
    }

    private fun initViews() {
        disableAutoFill()
    }

    private fun initObservers() {
        viewModel.event.observe(this, ::handle)
    }

    private fun handle(event: SaleViewModel.Event) {
        if (event.type == SaleViewModel.EventType.NAVIGATION) handleNavigation(event)
    }

    private fun disableAutoFill() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            window?.decorView?.importantForAutofill =
                View.IMPORTANT_FOR_AUTOFILL_NO_EXCLUDE_DESCENDANTS
        }
    }

    private fun handleNavigation(event: SaleViewModel.Event) {

        val fragment: Fragment = when (event) {
            is SaleViewModel.Event.NavigateToChooseArticle -> ChooseProductFragment.newInstance()
        }

        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in_right,
                R.anim.slide_out_left,
                R.anim.slide_in_left,
                R.anim.slide_out_right,
            )
            .replace(
                R.id.fragment_container_view,
                fragment,
                fragment::class.java.simpleName
            )
            .addToBackStack(null)
            .commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    companion object {
        fun launch(from: Context) = from.startActivity(Intent(from, SaleActivity::class.java))
    }
}
