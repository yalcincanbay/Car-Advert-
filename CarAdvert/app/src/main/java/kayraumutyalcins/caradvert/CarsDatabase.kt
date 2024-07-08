package kayraumutyalcins.caradvert

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Car::class], version = 5)
abstract class CarsDatabase : RoomDatabase() {
    abstract fun carDao(): CarDao
}

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE cars ADD COLUMN make TEXT NOT NULL DEFAULT ''")
        database.execSQL("ALTER TABLE cars ADD COLUMN year INTEGER NOT NULL DEFAULT 0")
        database.execSQL("ALTER TABLE cars ADD COLUMN mileage INTEGER NOT NULL DEFAULT 0")
        database.execSQL("ALTER TABLE cars ADD COLUMN color TEXT NOT NULL DEFAULT ''")
        database.execSQL("ALTER TABLE cars ADD COLUMN price REAL NOT NULL DEFAULT 0.0")
    }
}

val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // empty for now
    }
}

val MIGRATION_3_4 = object : Migration(3, 4) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE cars ADD COLUMN imageUri TEXT")
    }
}

val MIGRATION_4_5 = object : Migration(4, 5) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE cars ADD COLUMN description TEXT NOT NULL DEFAULT ''")
    }
}
