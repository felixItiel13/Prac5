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

    fun changeName(name: String) {
        state = state.copy(
            pointName = name
        )
    }

    fun changePrice(price: String) {
        state = state.copy(
            productPrice = price
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
            productPrice = touristicPoint.price,
            productId = touristicPoint.id
        )
    }

    fun createProduct() {
        val touristicPoint =
            TouristicPoint(
                (state.productId ?: UUID.randomUUID()) as Int,
                state.pointName,
                state.productPrice.toDouble()
            )
        viewModelScope.launch {
            dao.insertProduct(touristicPoint)
        }
        state = state.copy(
            pointName = "",
            productPrice = "",
            productId = null
        )
    }
}
