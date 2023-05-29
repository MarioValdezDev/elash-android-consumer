package mx.mariovaldez.elashstudioapp

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import mx.mariovaldez.elashstudioapp.data.repository.DefaultEmployeeRepository
import mx.mariovaldez.elashstudioapp.data.source.local.entities.LocalEmployee
import mx.mariovaldez.elashstudioapp.source.local.FakeEmployeeDao
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test

class DefaultLocalRepositoryTest {

    private var testDispatcher = UnconfinedTestDispatcher()
    private var testScope = TestScope(testDispatcher)

    private val localEmployees = listOf(
        LocalEmployee(
            "1234",
            "Mario",
            "Antonio",
            "Flores",
            "Valdez",
            "05/11/1995",
            "1234",
            "6672123456"
        ),
        LocalEmployee(
            "12345",
            "Mario",
            "Antonio",
            "Flores",
            "Valdez",
            "05/11/1995",
            "1234",
            "6672123456"
        )
    )

    private val localDataSource = FakeEmployeeDao(localEmployees)
    private val employeeRepository = DefaultEmployeeRepository(
        localDataSource,
        testDispatcher
    )

    @Test
    fun observeAll_exposesLocalData() = runTest {
        val employees = employeeRepository.observeAll().first()
        assertEquals(localEmployees, employees)
    }

    @Test
    fun onEmployeeCreation_local() = testScope.runTest {
        val employeeId = employeeRepository.create(
            localEmployees[0].firstName,
            localEmployees[0].secondName,
            localEmployees[0].lastName,
            localEmployees[0].secondLastName,
            localEmployees[0].bornDate,
            localEmployees[0].idOccupation,
            localEmployees[0].cellphone
        )

        val employees = localDataSource.observeAll().first()
        Assert.assertEquals(true, employees.map { it.idEmployee }.contains(employeeId))
    }
}
