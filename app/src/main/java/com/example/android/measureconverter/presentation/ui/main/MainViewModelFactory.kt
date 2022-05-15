package com.example.android.measureconverter.presentation.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.measureconverter.data.source.local.UnitsDao

class MainViewModelFactory(private val unitsDao: UnitsDao): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(unitsDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}