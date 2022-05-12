package com.example.android.measureconverter.data

enum class LengthUnit (var unitName: String, var convertingData: Double, var pluralName: String, val shortUnitName: String) {
    KILOMETER("kilometers", 1000.0, "kilometer", "km"),
    METER("meters", 1.0, "meter", "m"),
    CENTIMETER("centimeters", 0.01, "centimeter", "cm"),
    MILLIMETER("millimeters", 0.001, "millimeter", "mm"),
    MILE("miles", 1609.35, "mile", "mi"),
    FOOT("feet", 0.3048, "foot", "ft"),
    INCH("inches", 0.0254, "inch", "in"),
    YARD("yards", 0.9144, "yard", "yd");
companion object{
    val list = listOf<LengthUnit>(KILOMETER, METER, CENTIMETER, MILLIMETER, MILE, FOOT, INCH, YARD)
}
}

class ListOfUnits() {
    fun list(): List<LengthUnit> = listOf<LengthUnit>(
        LengthUnit.KILOMETER,
        LengthUnit.METER,
        LengthUnit.CENTIMETER,
        LengthUnit.MILLIMETER,
        LengthUnit.MILE,
        LengthUnit.FOOT,
        LengthUnit.INCH,
        LengthUnit.YARD
    )
}
