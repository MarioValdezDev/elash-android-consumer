package mx.mariovaldez.elashstudioapp.data.source.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import mx.mariovaldez.elashstudioapp.login.domain.model.User

@Entity(
    tableName = "user"
)
data class LocalUser(
    @PrimaryKey val username: String,
    val idUser: String,
    val password: String,
    val idEmployee: String,
    val idRole: String,
    val idStatus: String,
)

fun LocalUser.toExternal() = User(
    username = username,
    idRole = idRole,
    idStatus = idStatus,
    idUser = idUser,
    idEmployee = idEmployee
)
fun User.toLocal(password: String) = LocalUser(
    username = username,
    idRole = idRole,
    idStatus = idStatus,
    idUser = idUser,
    idEmployee = idEmployee,
    password = password
)

fun List<LocalUser>.toExternal() = map(LocalUser::toExternal)
