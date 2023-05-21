package com.mkiperszmid.emptyapp.home

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "touristic_point")
data class TouristicPoint(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val country: String,
    val city:String,
    val price: Double
)
