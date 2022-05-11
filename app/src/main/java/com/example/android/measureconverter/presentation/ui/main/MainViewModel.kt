package com.example.android.measureconverter.presentation.ui.main

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.measureconverter.data.LengthUnit
import com.example.android.measureconverter.data.ListOfUnits
import com.example.android.measureconverter.data.source.local.UnitsDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.concurrent.Flow

class MainViewModel(private val listOfUnits: UnitsDao) : ViewModel() {

    val list = listOfUnits.getAll("length")

    private var _leftChosenUnit = MutableLiveData<String>()
    val leftChosenUnit: LiveData<String> = _leftChosenUnit

    private val _rightChosenUnit = MutableLiveData<String>()
    val rightChosenUnit: LiveData<String> = _rightChosenUnit

    private val _result = MutableLiveData<String>()
    val result: LiveData<String> = _result

    fun changeLeftUnit(choice: String) {
        _leftChosenUnit.value = choice
    }

    fun changeRightUnit(choice: String) {
        _rightChosenUnit.value = choice
    }
}


