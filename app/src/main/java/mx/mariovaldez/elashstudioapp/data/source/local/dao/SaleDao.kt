package mx.mariovaldez.elashstudioapp.data.source.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import mx.mariovaldez.elashstudioapp.data.source.local.entities.LocalSale

@Dao
interface SaleDao {

    @Query("SELECT * FROM sale")
    fun observeAll(): Flow<List<LocalSale>>

    @Upsert
    suspend fun upsert(sale: LocalSale)
}
