package com.example.android.measureconverter.data.source.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android.measureconverter.domain.models.UnitToAdd

@Entity
data class Units (
    @PrimaryKey (autoGenerate = true) val id: Int,
    val type: String,
    @ColumnInfo(name = "unit_name")val unitName: String,
    @ColumnInfo(name = "short_unit_name")val shortUnitName: String,
    @ColumnInfo(name = "plural_name")val pluralName: String,
    @ColumnInfo(name = "converting_data")val convertingData: String
        )

fun List<Units>.asDomainModel(): List<UnitToAdd> {
    return map {
        UnitToAdd(
            id = it.id,
            type = it.type,
            unitName = it.unitName,
            shortUnitName = it.shortUnitName,
            pluralName = it.pluralName,
            convertingData = it.convertingData
        )
    }
}