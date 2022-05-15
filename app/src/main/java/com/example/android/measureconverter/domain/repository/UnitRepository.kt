package com.example.android.measureconverter.domain.repository

import com.example.android.measureconverter.domain.models.CalculatingResult
import com.example.android.measureconverter.domain.models.UnitDataForCalculating
import com.example.android.measureconverter.domain.models.UnitToAdd

interface UnitRepository {

    suspend fun addUnit(unit: UnitToAdd): Boolean

    suspend fun deleteUnit(unit: UnitToAdd): Boolean

}