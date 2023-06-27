package mx.mariovaldez.elashstudioapp.sale.presentation.processSale

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import mx.mariovaldez.elashstudioapp.R
import mx.mariovaldez.elashstudioapp.databinding.FragmentProcessSaleBinding
import mx.mariovaldez.elashstudioapp.ktx.viewBinding
import mx.mariovaldez.elashstudioapp.sale.presentation.SaleViewModel
import mx.mariovaldez.elashstudioapp.ui.bind


@AndroidEntryPoint
class ProcessSaleFragment : Fragment(R.layout.fragment_process_sale) {

    private val viewModel: ProcessSaleViewModel by viewModels()
    private val saleViewModel: SaleViewModel by activityViewModels()

    private val binding: FragmentProcessSaleBinding by viewBinding(
        FragmentProcessSaleBinding::bind
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
    }

    private fun setupViews() {
        with(binding){
            toolbar.bind(
                title = getString(R.string.frag_process_sale_title)
            ){ }
        }
    }

    companion object {
        fun newInstance(): ProcessSaleFragment = ProcessSaleFragment()
    }
}
