package com.example.android.measureconverter.presentation.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.measureconverter.domain.models.CalculatedResult
import com.example.android.measureconverter.domain.models.UnitToAdd
import com.example.android.measureconverter.domain.usecase.AddNewUnitUseCase
import com.example.android.measureconverter.domain.usecase.CalculateResultUseCase
import com.example.android.measureconverter.domain.usecase.DeleteUnitUseCase
import com.example.android.measureconverter.domain.usecase.GetUnitListUseCase
import kotlinx.coroutines.flow.Flow

class MainViewModel(
    val addNewUnitUseCase: AddNewUnitUseCase,
    val deleteUnitUseCase: DeleteUnitUseCase,
    val getUnitListUseCase: GetUnitListUseCase,
    val calculateResultUseCase: CalculateResultUseCase
) : ViewModel() {

    suspend fun getList(): Flow<List<UnitToAdd>> = getUnitListUseCase.execute("length") //todo

    suspend fun getCalculatedResultList(
        unit: UnitToAdd,
        amount: String
    ): Flow<List<CalculatedResult>> = calculateResultUseCase.execute(unit, amount.toDouble())

    var currentUnit: UnitToAdd? = null

    private var _leftChosenUnit = MutableLiveData<String>()
    val leftChosenUnit: LiveData<String> = _leftChosenUnit

        private val _rightChosenUnit = MutableLiveData<String>()
    val rightChosenUnit: LiveData<String> = _rightChosenUnit
//
//    private val _result = MutableLiveData<String>()
//    val result: LiveData<String> = _result
//
    private val _stringWithType = MutableLiveData<String>()
    val stringWithType = _stringWithType

    fun changeLeftUnit(choice: String) {
        _leftChosenUnit.value = choice
    }

    fun changeUnitOnUi(choice: String) {
        _rightChosenUnit.value = choice
    }

    fun changeTypeOnUi(type: String?) {
        val unit = when (type) {
            "length" -> "meters"
            "weight" -> "grams"
            "degrees" -> "degrees"
            //todo
            else -> "meters"
        }
        stringWithType.value = "How many $unit in one unit?"
    }

    suspend fun addNewItem(type: String, name: String, shortName: String, calculatingData: String) {
        addNewUnitUseCase.execute(type, name, shortName, name, calculatingData)
    }

//    fun calculate(unit: UnitToAdd, amount: String): {
//        calculateResultUseCase.execute(unit, amount.toDouble())
//    }
    fun currentUnit(unit: UnitToAdd) {
        currentUnit = unit
    }

}


