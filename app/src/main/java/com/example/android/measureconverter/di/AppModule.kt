package com.example.android.measureconverter.di

import android.content.Context
import com.example.android.measureconverter.data.source.local.UnitsDao
import com.example.android.measureconverter.domain.repository.UnitRepository
import com.example.android.measureconverter.domain.usecase.AddNewUnitUseCase
import com.example.android.measureconverter.domain.usecase.CalculateResultUseCase
import com.example.android.measureconverter.domain.usecase.DeleteUnitUseCase
import com.example.android.measureconverter.domain.usecase.GetUnitListUseCase
import com.example.android.measureconverter.presentation.ui.main.MainViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class AppModule(val context: Context) {

    @Provides
    fun provideContext(): Context {
        return context
    }

    @Provides
    fun provideViewModelFactory(
        addNewUnitUseCase: AddNewUnitUseCase,
        deleteUnitUseCase: DeleteUnitUseCase,
        getUnitListUseCase: GetUnitListUseCase,
        calculateResultUseCase: CalculateResultUseCase
    ): MainViewModelFactory {
        return MainViewModelFactory(
            getUnitListUseCase = getUnitListUseCase,
            deleteUnitUseCase = deleteUnitUseCase,
            addNewUnitUseCase = addNewUnitUseCase,
            calculateResultUseCase = calculateResultUseCase

        )
    }
}