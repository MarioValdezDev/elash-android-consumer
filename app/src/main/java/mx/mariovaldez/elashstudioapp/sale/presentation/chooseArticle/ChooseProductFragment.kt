package mx.mariovaldez.elashstudioapp.sale.presentation.chooseArticle

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import mx.mariovaldez.elashstudioapp.R
import mx.mariovaldez.elashstudioapp.databinding.FragmentChooseProductBinding
import mx.mariovaldez.elashstudioapp.ktx.viewBinding
import mx.mariovaldez.elashstudioapp.sale.presentation.SaleViewModel
import mx.mariovaldez.elashstudioapp.sale.presentation.adapters.ProductAdapter
import mx.mariovaldez.elashstudioapp.ui.bind

@AndroidEntryPoint
class ChooseProductFragment : Fragment(R.layout.fragment_choose_product) {

    private val saleViewModel: SaleViewModel by activityViewModels()
    private val viewModel: ChooseProductViewModel by viewModels()
    private val binding: FragmentChooseProductBinding by viewBinding(
        FragmentChooseProductBinding::bind,
    )

    private lateinit var adapter: ProductAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        with(binding) {
            toolbar.bind(
                title = "Seleccionar Artículo"
            ) { }
        }

    }

    companion object {
        fun newInstance(): ChooseProductFragment = ChooseProductFragment()
    }
}
