package mx.mariovaldez.elashstudioapp.data.source.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "consumer"
)
data class LocalConsumer(
    @PrimaryKey val idConsumer: String,
    val consumerName: String,
    val consumerSecondName: String,
    val consumerLastName: String,
    val consumerSecondLastName: String,
    val bornDate: String,
    val cellphone: String,
){
}
