package mx.mariovaldez.elashstudioapp.data.source.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import mx.mariovaldez.elashstudioapp.data.source.local.entities.LocalUser
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun observeAll(): Flow<List<LocalUser>>

    @Upsert
    suspend fun upsert(user: LocalUser)

    @Query("SELECT * FROM user WHERE username = :username")
    fun observeUser(username: String): LocalUser?

}
