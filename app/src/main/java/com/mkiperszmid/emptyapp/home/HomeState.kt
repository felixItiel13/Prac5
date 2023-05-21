package com.mkiperszmid.emptyapp.home

data class HomeState(
    val touristicPoints: List<TouristicPoint> = emptyList(),
    val pointName: String = "",
    val pointPrice: Double? = 0.0,
    val pointId: Int? = 0,
    val country: String = "",
    val city: String = "",
)
