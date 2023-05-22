package com.mkiperszmid.emptyapp.daos

import androidx.room.*
import com.mkiperszmid.emptyapp.entities.Language
import kotlinx.coroutines.flow.Flow

@Dao
interface LanguageDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertLanguage(language: Language)

    @Query("SELECT * FROM language")
    fun getAllLanguage(): Flow<List<Language>>

    @Query("SELECT * FROM language WHERE NAME = :name")
    fun getLanguage(name:String): Flow<Language>

    @Delete
    suspend fun deleteLanguage(language:Language)
}