package com.example.android.measureconverter.data

import com.example.android.measureconverter.domain.models.UnitToAdd

interface UnitStorage {

    suspend fun add(unitToAdd: UnitToAdd): Boolean

    suspend fun delete(unitToAdd: UnitToAdd): Boolean

}
