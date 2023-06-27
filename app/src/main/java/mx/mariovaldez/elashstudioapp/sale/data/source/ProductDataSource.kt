package mx.mariovaldez.elashstudioapp.sale.data.source

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import mx.mariovaldez.elashstudioapp.data.source.local.dao.ProductDao
import mx.mariovaldez.elashstudioapp.data.source.local.entities.LocalProduct
import mx.mariovaldez.elashstudioapp.data.source.local.entities.toExternal
import mx.mariovaldez.elashstudioapp.di.DefaultDispatcher
import mx.mariovaldez.elashstudioapp.sale.domain.models.Product
import mx.mariovaldez.elashstudioapp.sale.domain.models.toUI
import mx.mariovaldez.elashstudioapp.sale.presentation.models.ProductUI
import mx.mariovaldez.elashstudioapp.util.createId
import javax.inject.Inject

internal class ProductDataSource @Inject constructor(
    private val productDao: ProductDao,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher,
) {

    suspend fun createProduct(name: String, price: Double, image: String): Product {

        val idProduct = withContext(dispatcher) {
            createId()
        }

        val localProduct = LocalProduct(
            idProduct = idProduct,
            name = name,
            price = price,
            image = image
        )
        productDao.upsert(localProduct)
        return localProduct.toExternal()
    }

    fun getProducts(): List<ProductUI> {
        return productDao.observeAll().map { it.toExternal().toUI() }
    }
}