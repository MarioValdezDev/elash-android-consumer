package mx.mariovaldez.elashstudioapp.data.source.local.relationship

import androidx.room.Embedded
import androidx.room.Relation
import mx.mariovaldez.elashstudioapp.data.source.local.entities.LocalConsumer
import mx.mariovaldez.elashstudioapp.data.source.local.entities.LocalEmployee
import mx.mariovaldez.elashstudioapp.data.source.local.entities.LocalProduct
import mx.mariovaldez.elashstudioapp.data.source.local.entities.LocalSale

data class ProductWithSales(

    @Embedded val sale: LocalSale,

    @Relation(
        parentColumn = "idProduct",
        entityColumn = "idProduct",
    ) val product: LocalProduct,

    @Relation(
        parentColumn = "idProduct",
        entityColumn = "idProduct",
    )

    val employee: LocalEmployee,

    @Relation(
        parentColumn = "idConsumer",
        entityColumn = "idConsumer",
    )

    val consumer: LocalConsumer,
) {}
