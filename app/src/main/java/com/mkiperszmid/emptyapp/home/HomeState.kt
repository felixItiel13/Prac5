package com.mkiperszmid.emptyapp.home

data class HomeState(
    val touristicPoints: List<TouristicPoint> = emptyList(),
    val productName: String = "",
    val productPrice: String = "",
    val productId: String? = null
)
