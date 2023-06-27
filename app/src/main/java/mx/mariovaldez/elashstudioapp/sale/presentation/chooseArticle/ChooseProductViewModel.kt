package mx.mariovaldez.elashstudioapp.sale.presentation.chooseArticle

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import mx.mariovaldez.elashstudioapp.sale.domain.usecases.CreateProduct
import mx.mariovaldez.elashstudioapp.sale.domain.usecases.GetProducts
import mx.mariovaldez.elashstudioapp.sale.presentation.models.ProductUI
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
internal class ChooseProductViewModel @Inject constructor(
    private val getProducts: GetProducts,
    private val createProduct: CreateProduct,
) : ViewModel() {

    private lateinit var product: ProductUI

    private val _state: MutableStateFlow<State> = MutableStateFlow(State.IncompleteRequirements)
    val state: StateFlow<State> get() = _state

    init {
        viewModelScope.launch {
            kotlin.runCatching {
                createProduct(
                    name = "Pestañas Volumen",
                    price = 500.toDouble(),
                    image = "",
                )
                createProduct(
                    name = "Pestañas Clásicas",
                    price = 300.toDouble(),
                    image = "",
                )
            }
        }
    }

    fun findProducts() {
        viewModelScope.launch {
            _state.value = State.Loading
            kotlin.runCatching {
                getProducts()
            }
                .onSuccess {
                    _state.value = State.GetSuccess(it)
                }
                .onFailure {
                    Timber.e(it)
                    _state.value = State.ShowError
                }
        }
    }

    fun onProductSelected(product: ProductUI) {
        this.product = product
        _state.value = State.CompleteRequirements
    }

    fun getProductSelected(): ProductUI = product

    sealed class State {
        object Loading : State()
        object CompleteRequirements : State()
        object IncompleteRequirements : State()
        object ShowError : State()
        data class GetSuccess(val products: List<ProductUI>) : State()
    }
}
