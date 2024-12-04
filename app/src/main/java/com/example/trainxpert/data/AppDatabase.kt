package com.example.trainxpert.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import com.example.trainxpert.model.SportSession
import com.example.trainxpert.model.ActivityItem


@Database(entities = [ActivityItem::class, SportSession::class], version = 2)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun sportSessionDao(): SportSessionDao
    abstract fun activityDao() : ActivityDao


    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "sportsessions_db"
                ).addMigrations(MIGRATION_1_2).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

val MIGRATION_1_2 = object : androidx.room.migration.Migration(1, 2) {
    override fun migrate(database: androidx.sqlite.db.SupportSQLiteDatabase) {
        // Créez la table "activities" si elle n'existe pas
        database.execSQL(
            """
            CREATE TABLE IF NOT EXISTS `activities` (
                `id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                `title` TEXT NOT NULL,
                `subtitle` TEXT NOT NULL,
                `imageResId` INTEGER NOT NULL,
                `category` TEXT NOT NULL
            )
            """.trimIndent()
        )
    }
}




