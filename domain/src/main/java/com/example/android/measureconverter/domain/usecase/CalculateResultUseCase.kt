package com.example.android.measureconverter.domain.usecase

import com.example.android.measureconverter.domain.models.CalculatedResult
import com.example.android.measureconverter.domain.models.UnitToAdd
import com.example.android.measureconverter.domain.repository.UnitRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.math.BigDecimal
import java.math.BigDecimal.ROUND_UP
import java.math.RoundingMode

class CalculateResultUseCase(private val userRepository: UnitRepository) {

    suspend fun execute(unit: UnitToAdd, amount: Double): Flow<List<CalculatedResult>> {
        val convertingData = unit.convertingData.toDouble() * amount
        val list = userRepository.getUnitList(unit.type).map { it.asAppModule(convertingData) }
        return list
    }

    fun List<UnitToAdd>.asAppModule(convertingData: Double): List<CalculatedResult> {
        return map {
            if (convertingData == 0.0) {
                CalculatedResult(result = "0", it.pluralName)
            } else {
                val res = BigDecimal(convertingData.toString()).divide(
                    BigDecimal(it.convertingData),
                    10,
                    RoundingMode.HALF_EVEN
                )
                    .stripTrailingZeros()
                    .toPlainString()
                if (res.toDouble() == 1.0) {
                    CalculatedResult(
                        result = res,
                        unitName = it.unitName,
                    )
                } else CalculatedResult(
                    result = res,
                    unitName = it.pluralName,
                )
            }
        }
    }
}