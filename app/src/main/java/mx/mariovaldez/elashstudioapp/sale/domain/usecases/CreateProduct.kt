package mx.mariovaldez.elashstudioapp.sale.domain.usecases

import dagger.Reusable
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import mx.mariovaldez.elashstudioapp.di.DefaultDispatcher
import mx.mariovaldez.elashstudioapp.sale.data.repository.ProductRepository
import javax.inject.Inject

@Reusable
internal class CreateProduct @Inject constructor(
    private val repository: ProductRepository,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher,
) {

    suspend operator fun invoke(name:String, price: Double, image: String,) = withContext(dispatcher) {
        repository.createProduct(name, price, image)
    }
}
