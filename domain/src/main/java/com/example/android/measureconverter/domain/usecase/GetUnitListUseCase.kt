package com.example.android.measureconverter.domain.usecase

import com.example.android.measureconverter.domain.models.UnitToAdd
import com.example.android.measureconverter.domain.repository.UnitRepository
import kotlinx.coroutines.flow.Flow

class GetUnitListUseCase(private val unitRepository: UnitRepository) {

    suspend fun execute(type: String): Flow<List<UnitToAdd>> {
        return unitRepository.getUnitList(type)
    }

}