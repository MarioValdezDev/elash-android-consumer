package mx.mariovaldez.elashstudioapp.data.source.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import mx.mariovaldez.elashstudioapp.data.source.local.entities.LocalEmployee

@Dao
interface EmployeeDao {

    @Query("SELECT * FROM employee")
    fun observeAll(): Flow<List<LocalEmployee>>

    @Upsert
    suspend fun upsert(employee: LocalEmployee)
}
