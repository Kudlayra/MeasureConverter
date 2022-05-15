package com.example.android.measureconverter.data.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = arrayOf(Units::class), version = 1)
abstract class UnitsDatabase : RoomDatabase() {

    abstract fun unitsDao(): UnitsDao

    companion object {
        private var database: UnitsDatabase? = null

        @Synchronized
        fun getInstance(context: Context): UnitsDatabase {
            return if (database == null) {
                database = Room.databaseBuilder(context, UnitsDatabase::class.java, "db").build()
                database as UnitsDatabase
            } else database as UnitsDatabase
        }
    }
}
