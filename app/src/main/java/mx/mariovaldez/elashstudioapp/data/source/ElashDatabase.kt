package mx.mariovaldez.elashstudioapp.data.source

import androidx.room.Database
import androidx.room.RoomDatabase
import mx.mariovaldez.elashstudioapp.data.source.local.dao.CheckDao
import mx.mariovaldez.elashstudioapp.data.source.local.dao.ConsumerDao
import mx.mariovaldez.elashstudioapp.data.source.local.dao.EmployeeDao
import mx.mariovaldez.elashstudioapp.data.source.local.dao.OccupationsDao
import mx.mariovaldez.elashstudioapp.data.source.local.dao.ProductDao
import mx.mariovaldez.elashstudioapp.data.source.local.dao.SaleDao
import mx.mariovaldez.elashstudioapp.data.source.local.dao.UserDao
import mx.mariovaldez.elashstudioapp.data.source.local.entities.LocalChecker
import mx.mariovaldez.elashstudioapp.data.source.local.entities.LocalConsumer
import mx.mariovaldez.elashstudioapp.data.source.local.entities.LocalEmployee
import mx.mariovaldez.elashstudioapp.data.source.local.entities.LocalOccupation
import mx.mariovaldez.elashstudioapp.data.source.local.entities.LocalProduct
import mx.mariovaldez.elashstudioapp.data.source.local.entities.LocalSale
import mx.mariovaldez.elashstudioapp.data.source.local.entities.LocalUser

@Database(
    entities = [
        LocalEmployee::class,
        LocalSale::class,
        LocalProduct::class,
        LocalConsumer::class,
        LocalChecker::class,
        LocalOccupation::class,
        LocalUser::class
    ],
    version = 1,
    exportSchema = false
)
abstract class ElashDatabase : RoomDatabase() {

    abstract fun employeeDao(): EmployeeDao
    abstract fun consumerDao(): ConsumerDao
    abstract fun saleDao(): SaleDao
    abstract fun occupationDao(): OccupationsDao
    abstract fun checkDao(): CheckDao
    abstract fun productDao(): ProductDao
    abstract fun userDao(): UserDao
}
