package com.example.android.measureconverter.data.repository

import com.example.android.measureconverter.data.UnitStorage
import com.example.android.measureconverter.data.source.local.UnitsDao
import com.example.android.measureconverter.domain.models.UnitToAdd
import com.example.android.measureconverter.domain.repository.UnitRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.toList

class UnitRepositoryImpl(private val unitStorage: UnitStorage): UnitRepository {

    override suspend fun addUnit(unit: UnitToAdd): Boolean {
        return unitStorage.add(unit)
    }

    override suspend fun deleteUnit(unit: UnitToAdd): Boolean {
        return unitStorage.delete(unit)
    }

    override suspend fun getUnitList(): Flow<List<UnitToAdd>> {
        return unitStorage.get()
    }


}