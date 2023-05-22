package com.mkiperszmid.emptyapp.daos

import androidx.room.*
import com.mkiperszmid.emptyapp.entities.Country
import kotlinx.coroutines.flow.Flow
@Dao
interface CountryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountry(country: Country)


    @Query("SELECT * FROM country")
    fun getAllCountry(): Flow<List<Country>>

    @Query("SELECT * FROM COUNTRY WHERE NAME = :name")
    fun getCountry(name:String): Flow<Country>

    @Query("SELECT * FROM COUNTRY WHERE CODE = :name LIMIT 1")
    fun getCountryByCode(name:String): Flow<List<Country>>

    @Delete
    suspend fun deleteCountry(country: Country)

}