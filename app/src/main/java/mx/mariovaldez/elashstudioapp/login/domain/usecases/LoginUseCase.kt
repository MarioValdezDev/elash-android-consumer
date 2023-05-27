package mx.mariovaldez.elashstudioapp.login.domain.usecases

import dagger.Reusable
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import mx.mariovaldez.elashstudioapp.di.DefaultDispatcher
import mx.mariovaldez.elashstudioapp.login.data.repository.LoginRepository
import mx.mariovaldez.elashstudioapp.login.domain.model.User
import javax.inject.Inject

@Reusable
internal class LoginUseCase @Inject constructor(
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher,
    private val repository: LoginRepository,
) {

    suspend operator fun invoke(username:String, password:String): User? = withContext(dispatcher) {
        repository.login(username, password)
    }
}
