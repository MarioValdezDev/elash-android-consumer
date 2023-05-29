package mx.mariovaldez.elashstudioapp.login.domain.usecases

import dagger.Reusable
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import mx.mariovaldez.elashstudioapp.di.DefaultDispatcher
import mx.mariovaldez.elashstudioapp.login.data.repository.LoginRepository
import mx.mariovaldez.elashstudioapp.login.domain.model.User
import javax.inject.Inject

@Reusable
internal class RegisterDefaultUserUserUseCase @Inject constructor(
    private val repository: LoginRepository,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(): User = withContext(dispatcher) {
        repository.createDefaultAdminUser()
    }
}
