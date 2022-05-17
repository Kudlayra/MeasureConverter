package com.example.android.measureconverter.domain.repository

import com.example.android.measureconverter.domain.models.UnitToAdd
import kotlinx.coroutines.flow.Flow


interface UnitRepository {

    suspend fun addUnit(unit: UnitToAdd): Boolean

    suspend fun deleteUnit(unit: UnitToAdd): Boolean

    suspend fun getUnitList(): Flow<List<UnitToAdd>>

}