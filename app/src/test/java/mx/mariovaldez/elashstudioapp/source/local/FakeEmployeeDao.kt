package mx.mariovaldez.elashstudioapp.source.local

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import mx.mariovaldez.elashstudioapp.data.source.local.dao.EmployeeDao
import mx.mariovaldez.elashstudioapp.data.source.local.entities.LocalEmployee

class FakeEmployeeDao(initialEmployees: List<LocalEmployee>) : EmployeeDao {

    private val _employees = initialEmployees.toMutableList()
    private val employeesStream = MutableStateFlow(_employees.toList())

    override fun observeAll(): Flow<List<LocalEmployee>> = employeesStream

    override suspend fun upsert(employee: LocalEmployee) {
        _employees.removeIf { it.idEmployee == employee.idEmployee }
        _employees.add(employee)
        employeesStream.emit(_employees)
    }
}
