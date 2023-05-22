package com.mkiperszmid.emptyapp.states

import com.mkiperszmid.emptyapp.entities.Country

data class CountryState(
    val countryList: List<Country> = listOf(
        Country("MEX","Mexico","America","Center",60f,"Capitalism"),
        Country("USA","United States","America","Center",60f,"Capitalism"),
        Country("COL","Colombia","America","Center",60f,"Capitalism"),
        Country("SPA","Spain","Europe","Center",60f,"Capitalism"),
    ),
    val code:String = "",
    val name: String = "",
    val continent: String = "",
    val region:String = "",
    val lifeExpectancy: String = "",
    val governmentForm: String = ""
)