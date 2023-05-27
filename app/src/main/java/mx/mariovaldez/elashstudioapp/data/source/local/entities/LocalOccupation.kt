package mx.mariovaldez.elashstudioapp.data.source.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "occupations"
)
data class LocalOccupation(
    @PrimaryKey val idOccupation: String,
    val occupationName: String,
) {
}
