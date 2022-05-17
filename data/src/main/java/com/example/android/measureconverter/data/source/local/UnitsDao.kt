package com.example.android.measureconverter.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.android.measureconverter.domain.models.UnitToAdd
import kotlinx.coroutines.flow.Flow


@Dao
interface UnitsDao {

    @Query("SELECT * FROM units")
    fun getAll(): Flow<List<Units>>

    @Insert
    suspend fun addNewUnit(unit: Units)

    @Query("DELETE FROM units WHERE id = :id")
    suspend fun deleteUnit(id: String)

}