package com.mkiperszmid.emptyapp.daos

import androidx.room.*
import com.mkiperszmid.emptyapp.entities.City
import com.mkiperszmid.emptyapp.entities.Country
import kotlinx.coroutines.flow.Flow

@Dao
interface CityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCity(city: City)
//    @Query("INSERT INTO city values(:id, :name,:countryCode,:population)")
//    suspend fun insertCity(id:Int?, name:String, countryCode:String, population:Double)

    @Query("SELECT * FROM city")
    fun getAllCity(): Flow<List<City>>

    @Query("SELECT * FROM city WHERE NAME = :name LIMIT 1")
    fun getCity(name:String): Flow<List<City>>

    @Query("SELECT * FROM city WHERE countryCode = :code LIMIT 1")
    fun getCityByCountry(code:String): Flow<List<City>>

    @Delete
    suspend fun deleteCountry(country: Country)
}