package mx.mariovaldez.elashstudioapp.data.source.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import mx.mariovaldez.elashstudioapp.data.source.local.entities.LocalUser

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun observeAll(): Flow<List<LocalUser>>

    @Upsert
    suspend fun upsert(user: LocalUser)

    @Query("SELECT * FROM user WHERE username = :username")
    fun observeUser(username: String): LocalUser?
}
