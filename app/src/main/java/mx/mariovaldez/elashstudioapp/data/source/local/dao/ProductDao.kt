package mx.mariovaldez.elashstudioapp.data.source.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import mx.mariovaldez.elashstudioapp.data.source.local.entities.LocalProduct
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Query("SELECT * FROM product")
    fun observeAll(): Flow<List<LocalProduct>>

    @Upsert
    suspend fun upsert(product: LocalProduct)
}
