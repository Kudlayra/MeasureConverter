package com.example.android.measureconverter.domain.usecase

import com.example.android.measureconverter.domain.models.UnitToAdd
import com.example.android.measureconverter.domain.repository.UnitRepository

class DeleteUnitUseCase(private val unitRepository: UnitRepository) {

    suspend fun execute(unit: UnitToAdd) {
        unitRepository.deleteUnit(unit)
    }

}
