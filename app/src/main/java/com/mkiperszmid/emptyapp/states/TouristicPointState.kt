package com.mkiperszmid.emptyapp.states

import com.mkiperszmid.emptyapp.entities.TouristicPoint

data class TouristicPointState(
    val touristicPoints: List<TouristicPoint> = emptyList(),
    val safeTouristicPoints: List<TouristicPoint> = emptyList(),
    val pointName: String = "",
    val pointPrice: String ="",
    val pointId: String? ="",
    val country: String = "",
    val city: String = "",
    val search:String = "",
    val latitude:String ="",
    val longitude:String= ""
)
