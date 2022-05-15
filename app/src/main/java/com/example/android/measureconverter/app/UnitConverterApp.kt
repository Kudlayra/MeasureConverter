package com.example.android.measureconverter.app

import android.app.Application
import com.example.android.measureconverter.data.source.local.UnitsDatabase

class UnitConverterApp: Application () {

    val database: UnitsDatabase by lazy { UnitsDatabase.getInstance(this) }

    override fun onCreate() {
        super.onCreate()
    }

}