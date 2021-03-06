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


    suspend fun getList(type: String): Flow<List<UnitToAdd>> = getUnitListUseCase.execute(type)

    suspend fun delete(unit: UnitToAdd) = deleteUnitUseCase.execute(unit)

    suspend fun getCalculatedResultList(
        unit: UnitToAdd,
        amount: String
    ): Flow<List<CalculatedResult>> = calculateResultUseCase.execute(unit, amount.toDouble())

    var currentUnit: UnitToAdd? = null

    private var _checkedRadioButton = MutableLiveData<String>()
    val checkedRadioButton = _checkedRadioButton

    init {
        checkedRadioButton(LENGTH)
    }

    private val _selectedUnit = MutableLiveData<Int>()
    val selectedUnit = _selectedUnit

    private  val _lastSelectedUnit = MutableLiveData<Int>()
    val lastSelectedUnit = _lastSelectedUnit

    private val _selectedType = MutableLiveData<String>() //todo
    val selectedType = _selectedType

    private var _leftChosenUnit = MutableLiveData<String>()
    val leftChosenUnit: LiveData<String> = _leftChosenUnit

        private val _rightChosenUnit = MutableLiveData<String>()
    val rightChosenUnit: LiveData<String> = _rightChosenUnit

//    private val _stringWithType = MutableLiveData<String>()
//    val stringWithType = _stringWithType
//
//    fun changeLeftUnit(choice: String) {
//        _leftChosenUnit.value = choice
//    }

    fun changeUnitOnUi(choice: String) {
        _rightChosenUnit.value = choice
    }



    suspend fun addNewItem(type: String, name: String, shortName: String, calculatingData: String) {
        addNewUnitUseCase.execute(type, name, shortName, name, calculatingData)
    }

    fun currentUnit(unit: UnitToAdd) {
        currentUnit = unit
    }

    fun changeType(type: String) {
        _selectedType.value = type
    }

    suspend fun delete () {
        deleteUnitUseCase.execute(currentUnit!!)
    }

    companion object {
        const val LENGTH = "length"
        const val WEIGHT = "weight"
    }
    init {
        changeType(LENGTH)
    }
    fun changeSelectedUnit(position: Int) {
        _lastSelectedUnit.value = _selectedUnit.value ?: 0
        _selectedUnit.value = position
    }

//    fun changeTypeOnUi(string: String) {
//        _stringWithType.value = string
//    }

    fun checkedRadioButton(type: String) {
        _checkedRadioButton.value = type
    }

}


