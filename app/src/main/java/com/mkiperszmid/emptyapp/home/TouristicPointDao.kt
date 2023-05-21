package com.mkiperszmid.emptyapp.home

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TouristicPointDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(touristicPoint: TouristicPoint)

    @Query("SELECT * FROM touristic_point")
    fun getAllProducts(): Flow<List<TouristicPoint>>

    @Delete
    suspend fun deleteProduct(touristicPoint: TouristicPoint)
}
