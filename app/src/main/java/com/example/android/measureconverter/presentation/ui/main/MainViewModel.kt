package com.example.android.measureconverter.presentation.ui.main

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.measureconverter.data.LengthUnit
import com.example.android.measureconverter.data.ListOfUnits
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.concurrent.Flow

class MainViewModel(private val listOfUnits: ListOfUnits) : ViewModel() {

    val list = listOfUnits.list()

    private val _leftChosenUnit = MutableLiveData<String>()
    val leftChosenUnit: LiveData<String> = _leftChosenUnit

    private val _rightChosenUnit = MutableLiveData<String>()
    val rightChosenUnit: LiveData<String> = _leftChosenUnit

    private val _result = MutableLiveData<String>()
    val result: LiveData<String> = _result

}


