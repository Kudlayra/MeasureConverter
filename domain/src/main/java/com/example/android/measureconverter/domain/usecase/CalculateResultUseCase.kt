package com.example.android.measureconverter.domain.usecase

import com.example.android.measureconverter.domain.models.CalculatedResult
import com.example.android.measureconverter.domain.models.UnitToAdd
import com.example.android.measureconverter.domain.repository.UnitRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CalculateResultUseCase(private val userRepository: UnitRepository) {

    suspend fun execute(unit: UnitToAdd, amount: Double): Flow<List<CalculatedResult>> {
        val convertingData = unit.convertingData.toDouble() * amount
        val list = userRepository.getUnitList(unit.type).map { it.asAppModule(convertingData) }
        return list
    }

    fun List<UnitToAdd>.asAppModule(convertingData: Double): List<CalculatedResult> {
        return map {
            CalculatedResult(
            result = it.convertingData.toDouble() * convertingData,
            unitName = it.pluralName
                )
        }
    }
}