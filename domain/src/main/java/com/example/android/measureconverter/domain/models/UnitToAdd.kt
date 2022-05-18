package com.example.android.measureconverter.domain.models

data class UnitToAdd(val id: Int,
                val type: String,
                val unitName: String,
                val shortUnitName: String,
                val pluralName: String,
                val convertingData: String)