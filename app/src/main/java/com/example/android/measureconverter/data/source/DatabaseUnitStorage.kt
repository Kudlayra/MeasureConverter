package com.example.android.measureconverter.data.source

import com.example.android.measureconverter.data.UnitStorage
import com.example.android.measureconverter.data.source.local.Units
import com.example.android.measureconverter.data.source.local.UnitsDao
import com.example.android.measureconverter.domain.models.UnitToAdd
import com.example.android.measureconverter.domain.repository.UnitRepository

class DatabaseUnitStorage(private val dao: UnitsDao) : UnitStorage {



    override suspend fun add(unitToAdd: UnitToAdd): Boolean {
        //todo
        //dao.addNewUnit()
        return true
    }

    override suspend fun delete(unitToAdd: UnitToAdd): Boolean {
        dao.deleteUnit(unitToAdd.id.toString())
        return true
    }

}