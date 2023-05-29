package mx.mariovaldez.elashstudioapp.authentication.domain.models

import mx.mariovaldez.elashstudioapp.login.domain.model.User

internal data class UserSession(
    val user: User,
    val lastSession: String,
    val token: String,
)
