package com.example.android.measureconverter.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Units::class], version = 1, exportSchema = false)
abstract class UnitsDatabase : RoomDatabase() {

    abstract fun unitsDao(): UnitsDao

    companion object {
        @Volatile
        private var INSTANCE: UnitsDatabase? = null

        fun getDatabase(context: Context): UnitsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    UnitsDatabase::class.java,
                    "units_database")
                    .createFromAsset("database/converter_app_database.db")
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }

}