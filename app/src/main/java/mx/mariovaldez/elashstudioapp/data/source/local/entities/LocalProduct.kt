package mx.mariovaldez.elashstudioapp.data.source.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "product"
)
data class LocalProduct(
    @PrimaryKey val idProduct: String,
    val name: String,
    val price: String,
) {
}
