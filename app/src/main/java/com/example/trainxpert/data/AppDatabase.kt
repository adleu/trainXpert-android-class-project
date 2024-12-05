package com.example.trainxpert.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.trainxpert.model.SportSession
import com.example.trainxpert.model.ActivityItem

@Database(entities = [ActivityItem::class, SportSession::class], version = 3, exportSchema = true)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun sportSessionDao(): SportSessionDao
    abstract fun activityDao(): ActivityDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "sportsessions_db"
                )
                    .addMigrations(MIGRATION_1_2, MIGRATION_2_3) // Inclure les migrations
                    .fallbackToDestructiveMigration() // Supprime les données si aucune migration n'est fournie
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}

// Migration de version 1 à 2 : Ajouter la table "activities"
val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
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

// Migration de version 2 à 3 : Ajouter les colonnes "conseil" et "pratique" à la table "activities"
val MIGRATION_2_3 = object : androidx.room.migration.Migration(2, 3) {
    override fun migrate(database: androidx.sqlite.db.SupportSQLiteDatabase) {
        // Ajoutez les colonnes pratique et conseil si elles n'existent pas
        database.execSQL(
            "ALTER TABLE activities ADD COLUMN pratique TEXT NOT NULL DEFAULT ''"
        )
        database.execSQL(
            "ALTER TABLE activities ADD COLUMN conseil TEXT NOT NULL DEFAULT ''"
        )
    }
}






