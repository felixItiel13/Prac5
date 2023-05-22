package com.mkiperszmid.emptyapp.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mkiperszmid.emptyapp.daos.TouristicPointDao
import com.mkiperszmid.emptyapp.entities.TouristicPoint
import com.mkiperszmid.emptyapp.states.TouristicPointState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class TouristicPointViewModel(
    private val dao: TouristicPointDao
) : ViewModel() {
    var state by mutableStateOf(TouristicPointState())
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
    fun changeId(id: String) {
        state = state.copy(
            pointId= id
        )
    }
    fun changeName(name: String) {
        state = state.copy(
            pointName = name
        )
    }

    fun changePrice(price: String) {
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
    fun changeLatitude(latitude:String){
        state = state.copy(
            latitude= latitude
        )
    }
    fun changeLongitude(longitude:String){
        state = state.copy(
            longitude = longitude
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
            pointPrice = touristicPoint.price.toString(),
            pointId = touristicPoint.id.toString()
        )
    }
    fun searchPoint(search: String){
        viewModelScope.launch {
            dao.searchPoint(search).collectLatest {
                state = state.copy(
                    touristicPoints = it
                )
            }
        }
    }

    fun createProduct() {
        val touristicPoint =
            TouristicPoint(
                state.pointId!!.toInt(),
                state.pointName,
                state.country,
                state.city,
                state.pointPrice.toDouble(),
                state.latitude.toDouble(),
                state.longitude.toDouble()
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
            pointPrice ="",
            pointId = ""
        )
    }

    fun activeFilter(countryCode: String, city:String, minPrice:String, maxPrice:String){
        val list = state.touristicPoints
        var filteredList = list
        if(countryCode!= ""){
            filteredList = filteredList.filter{ point-> point.countryCode == countryCode }
        }
        if(city!=""){
            filteredList = filteredList.filter{ point-> point.city == city }
        }
        if(minPrice!=""){
            filteredList = filteredList.filter{ point -> point.price>=minPrice.toDouble()}
        }
        if(maxPrice!=""){
            filteredList = filteredList.filter{ point -> point.price<=maxPrice.toDouble()}
        }
        state = state.copy(touristicPoints = filteredList)
    }
    fun disableFilter (){
        viewModelScope.launch {
            dao.getAllProducts().collectLatest {
                state = state.copy(
                    touristicPoints = it
                )
            }
        }
    }
    fun isEmptySpace():Boolean{
        return state.pointId=="" || state.city=="" || state.pointName =="" || state.country =="" || state.pointPrice ==""
    }
}
