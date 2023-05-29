package mx.mariovaldez.elashstudioapp.data.source.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import mx.mariovaldez.elashstudioapp.data.source.local.entities.LocalConsumer

@Dao
interface ConsumerDao {

    @Query("SELECT * FROM consumer")
    fun observeAll(): Flow<List<LocalConsumer>>

    @Upsert
    suspend fun upsert(consumer: LocalConsumer)
}
