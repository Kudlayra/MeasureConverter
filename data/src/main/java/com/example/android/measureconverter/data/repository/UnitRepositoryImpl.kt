package com.example.android.measureconverter.data.repository

import com.example.android.measureconverter.data.UnitStorage
import com.example.android.measureconverter.domain.models.UnitToAdd
import com.example.android.measureconverter.domain.repository.UnitRepository

class UnitRepositoryImpl(private val unitStorage: UnitStorage): UnitRepository {

    override suspend fun addUnit(unit: UnitToAdd): Boolean {
        return unitStorage.add(unit)
    }

    override suspend fun deleteUnit(unit: UnitToAdd): Boolean {
        return unitStorage.delete(unit)
    }

}