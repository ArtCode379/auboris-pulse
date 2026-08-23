package auboris.strategy.auborispulse.di

import androidx.room.Room
import auboris.strategy.auborispulse.data.database.ZVOPDDatabase
import org.koin.dsl.module

private const val DB_NAME = "zvopd_db"

val databaseModule = module {
    single {
        Room.databaseBuilder(
            context = get(),
            klass = ZVOPDDatabase::class.java,
        name = DB_NAME
        ).build()
    }

    single { get<ZVOPDDatabase>().bookingDao()}

}