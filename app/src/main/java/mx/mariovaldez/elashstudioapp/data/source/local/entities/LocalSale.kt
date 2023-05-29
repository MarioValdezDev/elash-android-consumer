package mx.mariovaldez.elashstudioapp.data.source.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "sale"
)
data class LocalSale(
    @PrimaryKey val idSale: String,
    val idProduct: String,
    val idEmployee: String,
    val saleDate: String,
    val quantity: String,
    val idConsumer: String?
)
