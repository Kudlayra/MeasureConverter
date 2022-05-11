package com.example.android.measureconverter.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface UnitsDao {

    @Query("SELECT * FROM Units WHERE type = :unitType")
    fun getAll(unitType: String): Flow<List<Units>>

    @Insert
    fun addNewUnit(unit: Units)

    @Query("DELETE FROM Units WHERE id = :id")
    fun deleteUnit(id: String)

}