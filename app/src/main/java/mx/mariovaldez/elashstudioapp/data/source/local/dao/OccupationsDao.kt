package mx.mariovaldez.elashstudioapp.data.source.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import mx.mariovaldez.elashstudioapp.data.source.local.entities.LocalOccupation
import kotlinx.coroutines.flow.Flow

@Dao
interface OccupationsDao {

    @Query("SELECT * FROM occupations")
    fun observeAll(): Flow<List<LocalOccupation>>

    @Upsert
    suspend fun upsert(occupation: LocalOccupation)
}
