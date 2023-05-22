package com.mkiperszmid.emptyapp.viewModels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mkiperszmid.emptyapp.daos.CountryDao
import com.mkiperszmid.emptyapp.entities.Country
import com.mkiperszmid.emptyapp.states.CountryState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CountryViewModel(
    private val countryDao: CountryDao
) : ViewModel(){
    var state by mutableStateOf(CountryState())
        private set

    init {
        state.countryList.forEach { country ->
            viewModelScope.launch {
                countryDao.insertCountry(country)
            }
        }
//        if(state.countryList.isEmpty())
//        {
            viewModelScope.launch {
                countryDao.getAllCountry().collectLatest {
                    state = state.copy(
                        countryList = it
                    )
                }
            }
        //}
    }

    fun changeCode(code:String) {
        state = state.copy(
            code = code
        )
    }
    fun changeName(name: String) {
        state = state.copy(
            name = name
        )
    }

    fun changeContinent(continent: String) {
        state = state.copy(
            continent = continent
        )
    }

    fun changeRegion(region: String) {
        state = state.copy(
            region = region
        )
    }
    fun changeLifeExpectancy(lifeExpectancy: String) {
        state = state.copy(
            lifeExpectancy = lifeExpectancy
        )
    }
    fun changeGovernmentForm(governmentForm: String) {
        state = state.copy(
            governmentForm = governmentForm
        )
    }



    fun createProduct() {
        val country =
            Country(
                state.code,
                state.name,
                state.continent,
                state.region,
                state.lifeExpectancy.toFloat(),
                state.governmentForm
            )
        viewModelScope.launch {
            countryDao.insertCountry(country)
        }
        cleanState()
    }
    fun cleanState(){
        state = state.copy(
            code="",
            name = "",
        continent= "",
        region= "",
        lifeExpectancy= "",
        governmentForm= "",
        )
    }


    fun updateCountry(code: String){
        viewModelScope.launch {
            countryDao.getCountryByCode(code).collectLatest {
                if(it.isNotEmpty()) {
                    state = state.copy(
                        name = it[0].name,
                        code = it[0].code,
                        continent = it[0].continent,
                        region = it[0].region,
                        lifeExpectancy = it[0].lifeExpectancy.toString(),
                        governmentForm = it[0].governmentForm
                    )
                }else{
                    state = state.copy(
                        code="",
                        name = "",
                        continent= "",
                        region= "",
                        lifeExpectancy= "",
                        governmentForm= ""
                    )
                }
            }
        }
    }
    fun updateCountryByName(name: String){
        //var d = Country("",0,"","","",0f,"")
        viewModelScope.launch {
            countryDao.getCountry(name).collect{a->
                state = state.copy(
                    name = a.name,
                    continent = a.continent,
                    code = a.code,
                    region = a.region,
                    governmentForm = a.governmentForm,
                    lifeExpectancy = a.lifeExpectancy.toString()
                )
            }

        }
    }
}