package mx.mariovaldez.elashstudioapp.data.repository

import mx.mariovaldez.elashstudioapp.data.source.local.dao.EmployeeDao
import mx.mariovaldez.elashstudioapp.data.source.local.entities.LocalEmployee
import mx.mariovaldez.elashstudioapp.di.DefaultDispatcher
import mx.mariovaldez.elashstudioapp.util.createId
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DefaultEmployeeRepository @Inject constructor(
    private val localDataSource: EmployeeDao,
    @DefaultDispatcher private val dispatcher: CoroutineDispatcher
) {

    fun observeAll(): Flow<List<LocalEmployee>> {
        return localDataSource.observeAll()
    }

    suspend fun create(
        firstName: String,
        secondName: String,
        lastName: String,
        secondLastName: String,
        bornDate: String,
        idOccupation: String,
        cellphone: String
    ): String {

        val idEmployee = withContext(dispatcher) {
            createId()
        }
        val employee = LocalEmployee(
            idEmployee,
            firstName,
            secondName,
            lastName,
            secondLastName,
            bornDate,
            idOccupation,
            cellphone
        )
        localDataSource.upsert(employee)
        return idEmployee
    }
}