package com.mkiperszmid.emptyapp.states

data class FilterState (
    val country: String ="",
    val city: String ="",
    val minPrice:String= "",
    val maxPrice:String= "",
    val active:Boolean = false
        )