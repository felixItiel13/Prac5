package com.mkiperszmid.emptyapp.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "touristic_point",
    foreignKeys = [
        ForeignKey(
            entity = Country::class,
            parentColumns = ["code"],
            childColumns = ["countryCode"]
        )
    ]
)
data class TouristicPoint(
    @PrimaryKey(autoGenerate = false)
    val id: Int?,
    val name: String,
    val countryCode: String,
    val city:String,
    val price: Double,
    val latitude:Double,
    val longitude:Double
)
