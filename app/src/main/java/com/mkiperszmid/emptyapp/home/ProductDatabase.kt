package com.mkiperszmid.emptyapp.home

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TouristicPoint::class], version = 1)
abstract class ProductDatabase : RoomDatabase() {
    abstract val dao: TouristicPointDao
}
