package mx.mariovaldez.elashstudioapp.authentication.data.local

import mx.mariovaldez.elashstudioapp.authentication.domain.models.Token
import mx.mariovaldez.elashstudioapp.authentication.domain.models.UserSession
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class SessionLocalDataSource @Inject constructor() {

    private var userSession : UserSession? = null
    private var token: Token? = null

    fun saveUserSession(userSession: UserSession){
        this.userSession = userSession
    }

    fun findUserSession(): UserSession? = userSession

    fun saveToken(token: Token){
        this.token = token
    }

    fun findToken(): Token? = token
}
