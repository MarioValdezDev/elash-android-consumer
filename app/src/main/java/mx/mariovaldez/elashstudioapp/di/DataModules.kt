package mx.mariovaldez.elashstudioapp.di

import android.content.Context
import androidx.room.Room
import mx.mariovaldez.elashstudioapp.data.source.ElashDatabase
import mx.mariovaldez.elashstudioapp.data.source.local.dao.CheckDao
import mx.mariovaldez.elashstudioapp.data.source.local.dao.ConsumerDao
import mx.mariovaldez.elashstudioapp.data.source.local.dao.EmployeeDao
import mx.mariovaldez.elashstudioapp.data.source.local.dao.OccupationsDao
import mx.mariovaldez.elashstudioapp.data.source.local.dao.ProductDao
import mx.mariovaldez.elashstudioapp.data.source.local.dao.SaleDao
import mx.mariovaldez.elashstudioapp.data.source.local.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): ElashDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            ElashDatabase::class.java,
            "Elash.db"
        ).build()
    }

    @Provides
    fun providesUserDao(database: ElashDatabase): UserDao = database.userDao()

    @Provides
    fun providesEmployeeDao(database: ElashDatabase): EmployeeDao = database.employeeDao()

    @Provides
    fun providesCheckDao(database: ElashDatabase): CheckDao = database.checkDao()

    @Provides
    fun providesOccupationDao(database: ElashDatabase): OccupationsDao = database.occupationDao()

    @Provides
    fun providesSaleDao(database: ElashDatabase): SaleDao = database.saleDao()

    @Provides
    fun providesConsumerDao(database: ElashDatabase): ConsumerDao = database.consumerDao()

    @Provides
    fun providesProductDao(database: ElashDatabase): ProductDao = database.productDao()

}
