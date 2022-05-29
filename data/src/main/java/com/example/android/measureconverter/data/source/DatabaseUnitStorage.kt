package com.example.android.measureconverter.data.source

import android.content.Context
import androidx.room.DatabaseView
import com.example.android.measureconverter.data.UnitStorage
import com.example.android.measureconverter.data.source.local.Units
import com.example.android.measureconverter.data.source.local.UnitsDao
import com.example.android.measureconverter.data.source.local.UnitsDatabase
import com.example.android.measureconverter.data.source.local.asDomainModel
import com.example.android.measureconverter.domain.models.UnitToAdd
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class DatabaseUnitStorage(private val database: UnitsDatabase) : UnitStorage {

    override suspend fun add(unitToAdd: UnitToAdd): Boolean {
        val newUnit = mapToUnit(unitToAdd)
        database.unitsDao().addNewUnit(newUnit)
        return true
    }

    override suspend fun delete(unitToAdd: UnitToAdd): Boolean {
        database.unitsDao().deleteUnit(unitToAdd.id.toString())
        return true
    }

    override suspend fun get(type: String): Flow<List<UnitToAdd>> {
        return database.unitsDao().getAll(type).map { it.asDomainModel() }
    }

    private fun mapToUnit(unit: UnitToAdd): Units {
        return Units(id = 0,type = unit.type, unitName = unit.unitName, shortUnitName = unit.shortUnitName, pluralName = unit.pluralName, convertingData = unit.convertingData)
    }

}