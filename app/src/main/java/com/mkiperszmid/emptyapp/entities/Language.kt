package com.mkiperszmid.emptyapp.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "language",
        foreignKeys = [
                ForeignKey(
                        entity = Country::class,
                        parentColumns = ["code"],
                        childColumns = ["countryCode"],
                )
        ]
)
class Language (
        @PrimaryKey(autoGenerate = true)
        val id: Int?,
        val name: String,
        val countryCode:String,
        val isOfficial:Boolean,
    )
