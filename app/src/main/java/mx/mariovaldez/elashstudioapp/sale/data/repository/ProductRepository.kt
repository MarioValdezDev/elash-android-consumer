package mx.mariovaldez.elashstudioapp.sale.data.repository

import dagger.Reusable
import kotlinx.coroutines.CoroutineDispatcher
import mx.mariovaldez.elashstudioapp.di.DefaultDispatcher
import mx.mariovaldez.elashstudioapp.sale.data.source.ProductDataSource
import mx.mariovaldez.elashstudioapp.sale.domain.models.Product
import mx.mariovaldez.elashstudioapp.sale.presentation.models.ProductUI
import javax.inject.Inject

@Reusable
internal class ProductRepository  @Inject constructor(
    private val localDataSource: ProductDataSource,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher,
){

    fun getProducts(): List<ProductUI> {
        return localDataSource.getProducts()
    }

    suspend fun createProduct(name: String, price: Double, image: String): Product {
        return localDataSource.createProduct(name, price, image)
    }
}
