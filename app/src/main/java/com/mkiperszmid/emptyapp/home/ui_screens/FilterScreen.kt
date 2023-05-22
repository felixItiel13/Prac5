package com.mkiperszmid.emptyapp.details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mkiperszmid.emptyapp.viewModels.CityViewModel
import com.mkiperszmid.emptyapp.viewModels.CountryViewModel
import com.mkiperszmid.emptyapp.viewModels.FilterViewModel
import com.mkiperszmid.emptyapp.viewModels.LanguageViewModel


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FilterScreen(filterViewModel: FilterViewModel, cityViewModel: CityViewModel, countryViewModel: CountryViewModel, navController: NavController){
    val state = filterViewModel.state
    val cities = cityViewModel.state.cityList.map{ it.name}
    val countries = countryViewModel.state.countryList.map { it.name }

    val citiesOptions = cities.toMutableList()
    citiesOptions.add(0,"-")
    val countriesOptions = countries.toMutableList()
    countriesOptions.add(0,"-")

    var selectedCity by remember { mutableStateOf(citiesOptions[0]) }
    selectedCity = if(state.city =="") {
        citiesOptions[0]
    }
    else{
        state.city
    }
    var selectedCountry by remember { mutableStateOf(countriesOptions[0]) }
    if(state.country=="") {
        selectedCountry = "-"
    }else{
        selectedCountry = state.country
    }

    var expandedCountry by remember{mutableStateOf(false)}
    var expandedCity by remember{mutableStateOf(false)}
    Box(Modifier.fillMaxSize(),contentAlignment = Alignment.Center){
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Text("This is filter screen")
            //Menu for country selection
            ExposedDropdownMenuBox(expanded = expandedCountry , onExpandedChange = {expandedCountry = !expandedCountry}) {
                TextField(value = selectedCountry,
                    onValueChange = {},
                    readOnly = true,
                    label = {Text("Country")},
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedCountry)
                    },
                    colors = ExposedDropdownMenuDefaults.textFieldColors()
                )
                ExposedDropdownMenu(expanded = expandedCountry, onDismissRequest = {expandedCountry = false}

                ) {
                    countriesOptions.forEach { selectedOption ->
                        DropdownMenuItem(onClick = {selectedCountry = selectedOption; expandedCountry = false; filterViewModel.changeCountry(selectedCountry)}) {
                            Text(text= selectedOption)
                        }
                    }
                }
            }

            //Menu for city selection
            ExposedDropdownMenuBox(expanded = expandedCity , onExpandedChange = {expandedCity = !expandedCity}) {
                TextField(value = selectedCity,
                    onValueChange = {},
                    readOnly = true,
                    label = {Text("City")},
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedCity)
                    },
                    colors = ExposedDropdownMenuDefaults.textFieldColors()
                )
                ExposedDropdownMenu(expanded = expandedCity, onDismissRequest = {expandedCity = false}

                ) {
                    citiesOptions.forEach { selectedOption ->
                        DropdownMenuItem(onClick = {selectedCity = selectedOption; expandedCity = false; filterViewModel.changeCity(selectedCity)}) {
                            Text(text= selectedOption)
                        }
                    }
                }
            }

            Row(horizontalArrangement = Arrangement.spacedBy(31.dp)){
                TextField(modifier = Modifier.width(125.dp), value = state.minPrice,
                    onValueChange = { val a = it.replace(" ","").replace("-","");filterViewModel.changeMinPrice(a)}, placeholder = { Text(
                    text = "Min price"
                )},
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    singleLine = true

                )
                TextField(modifier = Modifier.width(125.dp),value = state.maxPrice,
                    onValueChange = {val a = it.replace(" ","").replace("-","");filterViewModel.changeMaxPrice(a)}, placeholder = { Text(
                    text = "Max price"
                )},
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    singleLine = true
                )
            }
            Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
                Button(onClick = { navController.navigate("home");   }) {
                    Text("Go Back")
                }
                Button(onClick = { navController.navigate("home") }) {
                    Text("Apply")
                }
                Button(onClick = {  filterViewModel.clearState() }) {
                    Text("Clear")
                }
            }
        }
    }
}
