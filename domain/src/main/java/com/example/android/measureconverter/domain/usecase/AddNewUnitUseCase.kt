package com.example.android.measureconverter.domain.usecase

import com.example.android.measureconverter.domain.models.UnitToAdd
import com.example.android.measureconverter.domain.repository.UnitRepository

class AddNewUnitUseCase(private val unitRepository: UnitRepository) {

    suspend fun execute(type: String, name: String, shortName: String, pluralName: String, convertingData: String) {
        unitRepository.addUnit(UnitToAdd(0, type, name, shortName, pluralName, convertingData))
    }

}