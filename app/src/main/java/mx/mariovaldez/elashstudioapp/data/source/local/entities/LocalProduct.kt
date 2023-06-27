package mx.mariovaldez.elashstudioapp.data.source.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import mx.mariovaldez.elashstudioapp.sale.domain.models.Product

@Entity(
    tableName = "product"
)
data class LocalProduct(
    @PrimaryKey val idProduct: String,
    val name: String,
    val price: Double,
    val image: String,
)

fun LocalProduct.toExternal() = Product(
    idProduct = idProduct,
    name = name,
    price = price,
    image = image
)

fun Product.toLocal() = LocalProduct(
    idProduct = idProduct,
    name = name,
    price = price,
    image = image
)

fun List<LocalProduct>.toExternal() = map(LocalProduct::toExternal)
