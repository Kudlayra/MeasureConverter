package com.example.android.measureconverter.presentation.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.measureconverter.data.source.local.Units
import com.example.android.measureconverter.data.source.local.UnitsDao
import kotlinx.coroutines.flow.Flow

class MainViewModel(private val unitsDao: UnitsDao) : ViewModel() {

    fun fullList(): Flow<List<Units>> = unitsDao.getAll()

    private var _leftChosenUnit = MutableLiveData<String>()
    val leftChosenUnit: LiveData<String> = _leftChosenUnit

    private val _rightChosenUnit = MutableLiveData<String>()
    val rightChosenUnit: LiveData<String> = _rightChosenUnit

    private val _result = MutableLiveData<String>()
    val result: LiveData<String> = _result

    private val _stringWithType = MutableLiveData<String>()
    val stringWithType = _stringWithType

    fun addNewItem(){
    }

    fun changeLeftUnit(choice: String) {
        _leftChosenUnit.value = choice
    }

    fun changeRightUnit(choice: String) {
        _rightChosenUnit.value = choice
    }

    fun changeType(type: String?) {
        val unit = when(type) {
            "Length" -> "meters"
            "Weight" -> "grams"
            "Degrees" -> "degrees"
            //todo
            else -> "meters"
        }
        stringWithType.value = "How many $unit in one unit?"
    }

    suspend fun addNewItem(unit: Units) {

    }
}


