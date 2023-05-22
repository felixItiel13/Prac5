package com.mkiperszmid.emptyapp.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mkiperszmid.emptyapp.daos.CityDao
import com.mkiperszmid.emptyapp.entities.City
import com.mkiperszmid.emptyapp.states.CityState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

import android.util.Log;
class CityViewModel(
    private val cityDao: CityDao
) : ViewModel(){
    var state by mutableStateOf(CityState())
        private set

    init {
        if(state.cityList.isNotEmpty())
        {
            state.cityList.forEach { city ->
                viewModelScope.launch {
                    cityDao.insertCity(city)
                }
            }
        }
//        if(state.countryList.isEmpty())
//        {
        viewModelScope.launch {
            cityDao.getAllCity().collectLatest {
                state = state.copy(
                    cityList = it
                )
            }
        }
        //}
    }

    fun changeId(id: Int) {
        state = state.copy(
            id= id
        )
    }
    fun changeName(name: String) {
        state = state.copy(
            name = name
        )
    }

    fun changeCountryCode(countryCode: String) {
        state = state.copy(
            countryCode = countryCode
        )
    }

    fun changePopulation(population: String) {
        state = state.copy(
            population = population
        )
    }


    fun createCity() {
        val city =
            City(
                state.id,
                state.name,
                state.countryCode,
                state.population.toDouble(),
            )

        Log.i("results", city.toString())
        viewModelScope.launch {
            cityDao.insertCity(city)
        }
        cleanState()
    }
    fun cleanState(){
        state = state.copy(
            name = "",
            population = "",
            countryCode = ""
        )
    }
    fun updateCityList(code: String){
        viewModelScope.launch {
            cityDao.getCityByCountry(code).collectLatest {
                Log.i("results", code)
                if(it.isNotEmpty()) {
                    state = state.copy(
                        cityList = it
                    )
                    Log.i("results","It is not empty")
                }else{
                    state = state.copy(
                        cityList= emptyList()
                    )
                    Log.i("results","It is empty")
                }
            }
        }
    }
}
