package auboris.strategy.auborispulse.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import auboris.strategy.auborispulse.data.dao.BookingDao
import auboris.strategy.auborispulse.data.database.converter.Converters
import auboris.strategy.auborispulse.data.entity.BookingEntity

@Database(
    entities = [BookingEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class ZVOPDDatabase : RoomDatabase() {

    abstract fun bookingDao(): BookingDao
}

