package com.example.android.measureconverter.domain.models

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

class UnitToAdd(val id: Int,
                val type: String,
                val unitName: String,
                val shortUnitName: String,
                val pluralName: String,
                val convertingData: String)