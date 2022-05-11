package com.example.android.measureconverter.data.source.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android.measureconverter.data.ListOfUnits

@Entity
data class Units (
    @PrimaryKey val id: Int,
    val type: String,
    @ColumnInfo(name = "unit_name")val unitName: String,
    @ColumnInfo(name = "short_unit_name")val shortUnitName: String,
    @ColumnInfo(name = "plural_name")val pluralName: String,
    @ColumnInfo(name = "converting_data")val convertingData: Double
        )