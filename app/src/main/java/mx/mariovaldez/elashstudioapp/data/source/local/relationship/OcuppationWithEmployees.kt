package mx.mariovaldez.elashstudioapp.data.source.local.relationship

import androidx.room.Embedded
import androidx.room.Relation
import mx.mariovaldez.elashstudioapp.data.source.local.entities.LocalEmployee
import mx.mariovaldez.elashstudioapp.data.source.local.entities.LocalOccupation

data class OcuppationWithEmployees(
    @Embedded val employee: LocalEmployee,
    @Relation(
        parentColumn = "idEmployee",
        entityColumn = "idOccupation"
    )
    val occupation: LocalOccupation
)

// create transaction
