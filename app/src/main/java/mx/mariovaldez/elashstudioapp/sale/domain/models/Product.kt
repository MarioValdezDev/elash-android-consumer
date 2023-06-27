package mx.mariovaldez.elashstudioapp.sale.domain.models

import mx.mariovaldez.elashstudioapp.sale.presentation.models.ProductUI

data class Product(
    val idProduct: String,
    val name: String,
    val price: Double,
    val image: String,
)

fun Product.toUI():ProductUI = ProductUI(
    idProduct = idProduct,
    name = name,
    price = price,
    image = image
)
