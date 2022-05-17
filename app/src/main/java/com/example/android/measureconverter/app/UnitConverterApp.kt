package com.example.android.measureconverter.app

import android.app.Application
import com.example.android.measureconverter.data.source.local.UnitsDatabase
import com.example.android.measureconverter.di.AppComponent
import com.example.android.measureconverter.di.AppModule
import com.example.android.measureconverter.di.DaggerAppComponent

class UnitConverterApp: Application () {

    val database: UnitsDatabase by lazy { UnitsDatabase.getInstance(this) }

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(context = this))
            .build()
    }

}