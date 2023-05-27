package mx.mariovaldez.elashstudioapp.data.source.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "checker"
)
data class LocalChecker(
    @PrimaryKey val idCheck:String,
    val idEmployee: String,
    val latitude: String,
    val longitude: String,
    val fullDate: String,
    val deviceId: String,
){
}
