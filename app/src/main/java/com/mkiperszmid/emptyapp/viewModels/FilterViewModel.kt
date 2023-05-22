package com.mkiperszmid.emptyapp.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.mkiperszmid.emptyapp.states.FilterState

class FilterViewModel : ViewModel(){
    var state by mutableStateOf(FilterState())
        private set

    init {
        state = state.copy(
            country = "",
            city ="",
            minPrice ="",
            maxPrice ="",
        )
    }

    fun changeCountry(country:String) {
        state = state.copy(
            country = country
        )
    }
    fun changeCity(city: String) {
        state = state.copy(
            city = city
        )
    }

    fun changeMinPrice(price: String) {
        state = state.copy(
            minPrice = price
        )
    }
    fun changeMaxPrice(price: String) {
        state = state.copy(
            maxPrice = price
        )
    }
    fun clearState(){
        state = state.copy(
            minPrice ="",
            maxPrice ="",
            country = "",
            city = ""
        )
    }
    fun isActive():Boolean{
        state = state.copy(active = !((state.country== "" || state.country=="-") && (state.city=="" || state.city=="-") && (state.minPrice == "") && state.maxPrice == ""))
        return state.active
    }
}
