package com.example.android.measureconverter.presentation.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.measureconverter.data.ListOfUnits

class MainViewModelFactory: ViewModelProvider.Factory {

    private val listOfUnits by lazy(LazyThreadSafetyMode.NONE) { ListOfUnits() }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(listOfUnits = listOfUnits) as T
    }

}