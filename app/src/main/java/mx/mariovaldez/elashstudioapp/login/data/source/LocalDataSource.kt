package mx.mariovaldez.elashstudioapp.login.data.source

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import mx.mariovaldez.elashstudioapp.data.source.local.dao.UserDao
import mx.mariovaldez.elashstudioapp.data.source.local.entities.LocalUser
import mx.mariovaldez.elashstudioapp.data.source.local.entities.toExternal
import mx.mariovaldez.elashstudioapp.di.DefaultDispatcher
import mx.mariovaldez.elashstudioapp.login.domain.model.User
import mx.mariovaldez.elashstudioapp.util.createId
import mx.mariovaldez.elashstudioapp.util.encrypt
import javax.inject.Inject

internal class LocalDataSource @Inject constructor(
    private val userDao: UserDao,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher
) {

    fun login(username: String, password: String): User? {
        userDao.observeUser(username)?.let {
            if (it.password == password.encrypt()) {
                return it.toExternal()
            }
        }
        return null
    }

    suspend fun createUser(
        username: String,
        password: String,
        idEmployee: String,
        idRole: String,
        idStatus: String
    ): User {
        val idUser = withContext(dispatcher) {
            createId()
        }
        val user = LocalUser(
            username = username,
            idRole = idRole,
            idStatus = idStatus,
            idUser = idUser,
            idEmployee = idEmployee,
            password = password.encrypt()
        )
        userDao.upsert(
            user
        )
        return user.toExternal()
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
        val admin = LocalUser(
            "admin",
            "1",
            "admin".encrypt(),
            "1",
            "1",
            "1"
        )
        val employee= LocalUser(
            "elash",
            "1",
            "elash".encrypt(),
            "1",
            "2",
            "1"
        )
        userDao.upsert(admin)
        userDao.upsert(employee)
        return admin.toExternal()
    }
}
