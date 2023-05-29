package mx.mariovaldez.elashstudioapp.authentication.data.repository

import mx.mariovaldez.elashstudioapp.authentication.data.local.SessionLocalDataSource
import mx.mariovaldez.elashstudioapp.authentication.domain.models.Token
import mx.mariovaldez.elashstudioapp.authentication.domain.models.UserSession
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class SessionRepository @Inject constructor(
    private val sessionLocalDataSource: SessionLocalDataSource
) {

    fun findUserSession(): UserSession? = sessionLocalDataSource.findUserSession()

    fun saveToken(token: Token) = sessionLocalDataSource.saveToken(token)

    fun findToken(): Token? = sessionLocalDataSource.findToken()
}