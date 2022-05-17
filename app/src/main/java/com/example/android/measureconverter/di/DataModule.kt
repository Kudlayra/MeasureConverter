package com.example.android.measureconverter.di

import android.content.Context
import com.example.android.measureconverter.data.UnitStorage
import com.example.android.measureconverter.data.repository.UnitRepositoryImpl
import com.example.android.measureconverter.data.source.DatabaseUnitStorage
import com.example.android.measureconverter.data.source.local.UnitsDao
import com.example.android.measureconverter.data.source.local.UnitsDao_Impl
import com.example.android.measureconverter.data.source.local.UnitsDatabase
import com.example.android.measureconverter.domain.repository.UnitRepository
import dagger.Module
import dagger.Provides


@Module
class DataModule() {

    @Provides
    fun provideUnitsDatabase(context: Context): UnitsDatabase {
        return UnitsDatabase.getInstance(context)
    }
    @Provides
    fun provideDatabaseUnitStorage(database: UnitsDatabase): DatabaseUnitStorage {
        return DatabaseUnitStorage(database)
    }

    @Provides
    fun provideUnitRepository(databaseUnitStorage: DatabaseUnitStorage): UnitRepository {
        return UnitRepositoryImpl(unitStorage = databaseUnitStorage)
    }

}