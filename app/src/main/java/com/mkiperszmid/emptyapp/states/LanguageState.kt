package com.mkiperszmid.emptyapp.states

import com.mkiperszmid.emptyapp.entities.City
import com.mkiperszmid.emptyapp.entities.Language

data class LanguageState (
    val languageList: List<Language> = listOf(
        Language(0,"Spanish", "MEX", true),
        Language(1,"Spanish", "COL", true),
        Language(2,"Spanish", "SPA", true),
        Language(3,"English", "USA", true),
    ),
    val id: Int = 0,
    val name: String= "",
    val countryCode:String = "",
    val isOfficial:String =""
)