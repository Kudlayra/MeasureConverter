package com.example.android.measureconverter.di

import com.example.android.measureconverter.domain.repository.UnitRepository
import com.example.android.measureconverter.domain.usecase.AddNewUnitUseCase
import com.example.android.measureconverter.domain.usecase.DeleteUnitUseCase
import com.example.android.measureconverter.domain.usecase.GetUnitListUseCase
import dagger.Module
import dagger.Provides

@Module
class DomainModule {
    @Provides
    fun provideAddNewUnitUseCase(unitRepository: UnitRepository): AddNewUnitUseCase {
        return AddNewUnitUseCase(unitRepository)
    }
    @Provides
    fun provideDeleteUnitUseCase(unitRepository: UnitRepository): DeleteUnitUseCase {
        return DeleteUnitUseCase(unitRepository)
    }

    @Provides
    fun provideGetUnitListUseCase(unitRepository: UnitRepository): GetUnitListUseCase {
        return GetUnitListUseCase(unitRepository)
    }

}