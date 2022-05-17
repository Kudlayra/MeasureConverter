package com.example.android.measureconverter.di

import com.example.android.measureconverter.presentation.ui.AddItemFragment
import com.example.android.measureconverter.presentation.ui.main.MainFragment
import dagger.Component

@Component(modules = [AppModule::class, DomainModule::class, DataModule::class])
interface AppComponent {

    fun inject(mainFragment: MainFragment)
    fun inject(addItemFragment: AddItemFragment)


}