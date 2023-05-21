package com.mkiperszmid.emptyapp.home

data class HomeState(
    val touristicPoints: List<TouristicPoint> = emptyList(),
    val pointName: String = "",
    val productPrice: Double? = null,
    val productId: Int? = null
)
