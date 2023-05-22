package com.mkiperszmid.emptyapp.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "city",
    foreignKeys = [
        ForeignKey(
            entity = Country::class,
            parentColumns = ["code"],
            childColumns = ["countryCode"],
            )
        ],
    indices = [Index(value =["name"],unique = true)]
    )
data class City(

    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val name: String,
    val countryCode:String,
    val population:Double
){
}