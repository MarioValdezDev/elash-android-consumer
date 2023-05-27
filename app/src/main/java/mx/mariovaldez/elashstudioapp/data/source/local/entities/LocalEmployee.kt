package mx.mariovaldez.elashstudioapp.data.source.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "employee"
)
data class LocalEmployee(
    @PrimaryKey val idEmployee: String,
    val firstName: String,
    val secondName: String,
    val lastName: String,
    val secondLastName: String,
    val bornDate: String,
    val idOccupation: String,
    val cellphone: String,
) {
}
