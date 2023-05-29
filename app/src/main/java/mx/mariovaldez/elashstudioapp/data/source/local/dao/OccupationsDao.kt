package mx.mariovaldez.elashstudioapp.data.source.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import mx.mariovaldez.elashstudioapp.data.source.local.entities.LocalOccupation

@Dao
interface OccupationsDao {

    @Query("SELECT * FROM occupations")
    fun observeAll(): Flow<List<LocalOccupation>>

    @Upsert
    suspend fun upsert(occupation: LocalOccupation)
}
