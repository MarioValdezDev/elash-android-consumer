package mx.mariovaldez.elashstudioapp.data.source.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import mx.mariovaldez.elashstudioapp.data.source.local.entities.LocalChecker
import kotlinx.coroutines.flow.Flow

@Dao
interface CheckDao {

    @Query("SELECT * FROM checker")
    fun observeAll(): Flow<List<LocalChecker>>

    @Upsert
    suspend fun upsert(check: LocalChecker)
}
