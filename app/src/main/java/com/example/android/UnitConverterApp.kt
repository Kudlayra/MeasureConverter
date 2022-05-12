package com.example.android

import android.app.Application
import com.example.android.measureconverter.data.source.local.UnitsDatabase

class UnitConverterApp: Application () {

    val database: UnitsDatabase by lazy { UnitsDatabase.getInstance(this) }

}