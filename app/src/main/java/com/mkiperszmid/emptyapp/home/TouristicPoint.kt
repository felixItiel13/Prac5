package com.mkiperszmid.emptyapp.home

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TouristicPoint(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val name: String,
    val price: Double
)
