package sa.factory.naqdterminalposcas.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NaqdTerminalEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pos_cashierDao(): NaqdTerminalDao
}
