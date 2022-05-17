package com.example.android.measureconverter.data

import com.example.android.measureconverter.data.source.local.Units
import com.example.android.measureconverter.domain.models.UnitToAdd
import kotlinx.coroutines.flow.Flow

interface UnitStorage {

    suspend fun add(unitToAdd: UnitToAdd): Boolean

    suspend fun delete(unitToAdd: UnitToAdd): Boolean

    suspend fun get(): Flow<List<UnitToAdd>>

}
