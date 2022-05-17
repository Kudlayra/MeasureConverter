package com.example.android.measureconverter.presentation.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.measureconverter.data.source.local.UnitsDao
import com.example.android.measureconverter.domain.usecase.AddNewUnitUseCase
import com.example.android.measureconverter.domain.usecase.DeleteUnitUseCase
import com.example.android.measureconverter.domain.usecase.GetUnitListUseCase

class MainViewModelFactory(
    val addNewUnitUseCase: AddNewUnitUseCase,
    val deleteUnitUseCase: DeleteUnitUseCase,
    val getUnitListUseCase: GetUnitListUseCase
): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(
                addNewUnitUseCase = addNewUnitUseCase,
                deleteUnitUseCase = deleteUnitUseCase,
                getUnitListUseCase = getUnitListUseCase
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}