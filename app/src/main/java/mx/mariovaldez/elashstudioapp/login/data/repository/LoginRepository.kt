package mx.mariovaldez.elashstudioapp.login.data.repository

import dagger.Reusable
import kotlinx.coroutines.CoroutineDispatcher
import mx.mariovaldez.elashstudioapp.di.DefaultDispatcher
import mx.mariovaldez.elashstudioapp.login.data.source.LocalDataSource
import mx.mariovaldez.elashstudioapp.login.domain.model.User
import javax.inject.Inject

@Reusable
internal class LoginRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher
) {

    fun login(username: String, password: String): User? {
        return localDataSource.login(username, password)
    }

    suspend fun createUser(
        username: String,
        password: String,
        idEmployee: String,
        idRole: String,
        idStatus: String
    ): User {
        return localDataSource.createUser(
            username,
            password,
            idEmployee,
            idRole,
            idStatus
        )
    }

    suspend fun deleteUser(): User {
        return User(
            username = "admin",
            idRole = "1",
            idStatus = "1",
            idUser = "1",
            idEmployee = "1"
        )
    }

    suspend fun updateUser(): User {
        return User(
            username = "admin",
            idRole = "1",
            idStatus = "1",
            idUser = "1",
            idEmployee = "1"
        )
    }

    suspend fun createDefaultAdminUser(): User {
        return localDataSource.createDefaultAdminUser()
    }
}
