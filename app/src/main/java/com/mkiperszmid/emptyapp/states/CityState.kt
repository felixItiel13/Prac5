package com.mkiperszmid.emptyapp.states

import com.mkiperszmid.emptyapp.entities.City

data class CityState (
    val cityList: List<City> = listOf(
        City(null,"Mexicali","MEX",1000.0),
        City(null,"Madrid","SPA",3000.0),
        City(null,"Bogota","COL",4000.0),
        City(null,"Washington","USA",5001.0),
    ),
    val id: Int = 0,
    val name: String= "",
    val countryCode:String = "",
    val population:String = ""
)