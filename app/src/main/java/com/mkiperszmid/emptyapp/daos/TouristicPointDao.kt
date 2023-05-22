package com.mkiperszmid.emptyapp.daos

import androidx.room.*
import com.mkiperszmid.emptyapp.entities.TouristicPoint
import kotlinx.coroutines.flow.Flow

@Dao
interface TouristicPointDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(touristicPoint: TouristicPoint)

    @Query("SELECT * FROM touristic_point")
    fun getAllProducts(): Flow<List<TouristicPoint>>

    @Query("SELECT * FROM touristic_point WHERE name like '%'||:string||'%'")
    fun searchPoint(string: String):Flow<List<TouristicPoint>>
    @Delete
    suspend fun deleteProduct(touristicPoint: TouristicPoint)
}
