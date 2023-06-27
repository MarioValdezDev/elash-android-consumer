package mx.mariovaldez.elashstudioapp.sale.presentation.chooseArticle

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import mx.mariovaldez.elashstudioapp.R
import mx.mariovaldez.elashstudioapp.databinding.FragmentChooseProductBinding
import mx.mariovaldez.elashstudioapp.ktx.context
import mx.mariovaldez.elashstudioapp.ktx.exhaustive
import mx.mariovaldez.elashstudioapp.ktx.gone
import mx.mariovaldez.elashstudioapp.ktx.observe
import mx.mariovaldez.elashstudioapp.ktx.viewBinding
import mx.mariovaldez.elashstudioapp.ktx.visible
import mx.mariovaldez.elashstudioapp.sale.presentation.SaleViewModel
import mx.mariovaldez.elashstudioapp.sale.presentation.adapters.ProductAdapter
import mx.mariovaldez.elashstudioapp.sale.presentation.models.ProductUI
import mx.mariovaldez.elashstudioapp.ui.bind
import timber.log.Timber

@AndroidEntryPoint
class ChooseProductFragment : Fragment(R.layout.fragment_choose_product) {

    private val saleViewModel: SaleViewModel by activityViewModels()
    private val viewModel: ChooseProductViewModel by viewModels()
    private val binding: FragmentChooseProductBinding by viewBinding(
        FragmentChooseProductBinding::bind,
    )

    private lateinit var productAdapter: ProductAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        initObservers()
        viewModel.findProducts()
    }

    private fun initViews() {
        with(binding) {
            toolbar.bind(
                title = context.getString(R.string.frag_choose_prod_title)
            ) { }

            nextButton.setOnClickListener {
                println(viewModel.getProductSelected())
                saleViewModel.saveProduct(viewModel.getProductSelected())
                saleViewModel.onRequirementCompleted(
                    SaleViewModel.Event.NavigateToProcessSale()
                )
            }
        }
    }

    private fun initObservers() {
        viewModel.state.observe(viewLifecycleOwner, ::handle)
    }

    private fun handle(state: ChooseProductViewModel.State) {
        when (state) {
            ChooseProductViewModel.State.CompleteRequirements -> enableButton()
            is ChooseProductViewModel.State.GetSuccess -> setupRecycler(state.products)
            ChooseProductViewModel.State.IncompleteRequirements -> disableButton()
            ChooseProductViewModel.State.Loading -> showLoading()
            ChooseProductViewModel.State.ShowError -> {
                Timber.e("Error al obtener productos")
            }
        }.exhaustive
    }

    private fun showLoading() {
        binding.progressBar.visible()
        binding.nextButton.gone()
    }

    private fun hideLoading() {
        binding.progressBar.gone()
        binding.nextButton.visible()
    }

    private fun enableButton() {
        binding.nextButton.isEnabled = true
    }

    private fun disableButton() {
        binding.nextButton.isEnabled = false
    }

    private fun setupRecycler(products: List<ProductUI>) {
        productAdapter = ProductAdapter { product ->
            viewModel.onProductSelected(product)
        }.apply {
            addProducts(products)
        }
        with(binding.articleRecyclerView) {
            adapter = productAdapter
            adapter
        }
        hideLoading()
    }

    companion object {
        fun newInstance(): ChooseProductFragment = ChooseProductFragment()
    }
}
