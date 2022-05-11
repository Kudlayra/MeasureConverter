package com.example.android.measureconverter.presentation.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.measureconverter.data.ListOfUnits
import com.example.android.measureconverter.data.source.local.UnitsDao

class MainViewModelFactory(private val unitsDao: UnitsDao): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(listOfUnits = unitsDao) as T
    }

}