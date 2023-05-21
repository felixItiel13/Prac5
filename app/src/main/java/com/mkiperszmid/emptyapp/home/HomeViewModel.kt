package com.mkiperszmid.emptyapp.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.UUID

class HomeViewModel(
    private val dao: TouristicPointDao
) : ViewModel() {
    var state by mutableStateOf(HomeState())
        private set

    init {
        viewModelScope.launch {
            dao.getAllProducts().collectLatest {
                state = state.copy(
                    touristicPoints = it
                )
            }
        }
    }
    fun changeId(id: Int) {
        state = state.copy(
            pointId= id
        )
    }
    fun changeName(name: String) {
        state = state.copy(
            pointName = name
        )
    }

    fun changePrice(price: Double) {
        state = state.copy(
            pointPrice = price
        )
    }
    fun changeCountry(country: String) {
        state = state.copy(
            country = country
        )
    }

    fun changeCity(city: String) {
        state = state.copy(
            city = city
        )
    }


    fun deleteProduct(touristicPoint: TouristicPoint) {
        viewModelScope.launch {
            dao.deleteProduct(touristicPoint)
        }
    }

    fun editProduct(touristicPoint: TouristicPoint) {
        state = state.copy(
            pointName = touristicPoint.name,
            pointPrice = touristicPoint.price,
            pointId = touristicPoint.id
        )
    }

    fun createProduct() {
        val touristicPoint =
            TouristicPoint(
                state.pointId!!,
                state.pointName,
                state.country,
                state.city,
                state.pointPrice!!.toDouble()
            )
        viewModelScope.launch {
            dao.insertProduct(touristicPoint)
        }
        cleanState()
    }
    fun cleanState(){
        state = state.copy(
            pointName = "",
            city = "",
            country = "",
            pointPrice =0.0,
            pointId = 0
        )
    }
}
