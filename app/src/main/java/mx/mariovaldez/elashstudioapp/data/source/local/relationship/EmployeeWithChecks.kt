package mx.mariovaldez.elashstudioapp.data.source.local.relationship

import androidx.room.Embedded
import androidx.room.Relation
import mx.mariovaldez.elashstudioapp.data.source.local.entities.LocalChecker
import mx.mariovaldez.elashstudioapp.data.source.local.entities.LocalEmployee

data class EmployeeWithChecks(
    @Embedded val employee: LocalEmployee,
    @Relation(
        parentColumn = "idEmployee",
        entityColumn = "idEmployee"
    )
    val checks: List<LocalChecker>
)
