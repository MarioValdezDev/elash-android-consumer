package mx.mariovaldez.elashstudioapp.data.source.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import mx.mariovaldez.elashstudioapp.data.source.local.entities.LocalChecker

@Dao
interface CheckDao {

    @Query("SELECT * FROM checker")
    fun observeAll(): Flow<List<LocalChecker>>

    @Upsert
    suspend fun upsert(check: LocalChecker)
}
