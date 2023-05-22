package com.mkiperszmid.emptyapp.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "country",
    indices = [Index(value=["code"], unique=true)])
class Country (
    @PrimaryKey(autoGenerate = false)
    val code: String,
    val name: String,
    val continent: String,
    val region:String,
    val lifeExpectancy: Float,
    val governmentForm: String
)
