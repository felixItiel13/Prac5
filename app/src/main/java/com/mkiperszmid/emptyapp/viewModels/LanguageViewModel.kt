package com.mkiperszmid.emptyapp.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mkiperszmid.emptyapp.daos.LanguageDao
import com.mkiperszmid.emptyapp.entities.Country
import com.mkiperszmid.emptyapp.entities.Language
import com.mkiperszmid.emptyapp.states.CityState
import com.mkiperszmid.emptyapp.states.LanguageState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class LanguageViewModel(
    private val languageDao: LanguageDao
) : ViewModel() {
    var state by mutableStateOf(LanguageState())
        private set

    init {
        if (state.languageList.isNotEmpty()) {
            state.languageList.forEach { language ->
                viewModelScope.launch {
                    languageDao.insertLanguage(language)
                }
            }
        }
//        if(state.countryList.isEmpty())
//        {
        viewModelScope.launch {
            languageDao.getAllLanguage().collectLatest {
                state = state.copy(
                    languageList = it
                )
            }
        }
        //}
    }
    fun createLanguage(){
    val language=
            Language(
                null,
                state.name,
                state.countryCode,
                state.isOfficial.toBoolean()
            )
        viewModelScope.launch {
            languageDao.insertLanguage(language)
        }
        cleanState()

    }

    fun changeName(name:String){
        state = state.copy(name = name)
    }
    fun changeCountryCode(code:String){
        state = state.copy(countryCode = code)
    }
    fun changeIsOfficial(itIs:String){
        state = state.copy(isOfficial = itIs)
    }
    fun cleanState(){
        state = state.copy(
            id =0,
            name= "",
            countryCode = "-",
            isOfficial = ""
        )
    }
}