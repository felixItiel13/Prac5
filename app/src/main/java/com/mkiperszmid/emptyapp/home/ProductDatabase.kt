package com.mkiperszmid.emptyapp.home

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mkiperszmid.emptyapp.daos.CityDao
import com.mkiperszmid.emptyapp.daos.CountryDao
import com.mkiperszmid.emptyapp.daos.LanguageDao
import com.mkiperszmid.emptyapp.daos.TouristicPointDao
import com.mkiperszmid.emptyapp.entities.City
import com.mkiperszmid.emptyapp.entities.Country
import com.mkiperszmid.emptyapp.entities.Language
import com.mkiperszmid.emptyapp.entities.TouristicPoint

@Database(entities = [TouristicPoint::class, Country::class, City::class, Language::class], version = 2)
abstract class ProductDatabase : RoomDatabase() {
    abstract val dao: TouristicPointDao
    abstract val countryDao: CountryDao
    abstract val cityDao:CityDao
    abstract val languageDao: LanguageDao
}
