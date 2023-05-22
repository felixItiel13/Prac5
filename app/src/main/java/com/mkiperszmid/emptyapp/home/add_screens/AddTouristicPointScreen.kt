package com.mkiperszmid.emptyapp.home

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.mkiperszmid.emptyapp.viewModels.CityViewModel
import com.mkiperszmid.emptyapp.viewModels.CountryViewModel
import com.mkiperszmid.emptyapp.viewModels.TouristicPointViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddTouristicPointScreen(viewModel: TouristicPointViewModel, countryViewModel: CountryViewModel, cityViewModel: CityViewModel, navController: NavHostController) {
    //States
    val state = viewModel.state
    val countryState = countryViewModel.state
    val cityState = cityViewModel.state
    // Mapping names of country and cities
    var countryName = countryState.countryList.map { it.code }
    val countryOptions = countryName.toMutableList()
    countryOptions.add(0,"-")


    var cityName = cityState.cityList.map { it.name }
    val cityOptions = cityName.toMutableList()
    cityOptions.add(0,"-")


    // Remembered values
    var selectedCountry by remember { mutableStateOf(countryOptions[0])}
    viewModel.changeCountry(selectedCountry)

    var selectedCity by remember { mutableStateOf(cityOptions[0])}
    viewModel.changeCity(selectedCity)

    var expandedCountry by remember { mutableStateOf(false) }
    var expandedCity by remember { mutableStateOf(false) }

    if(state.country!="" && state.country!= "-"){
        if(state.country != cityState.countryCode) cityViewModel.updateCityList(state.country)
        cityViewModel.changeCountryCode(state.country)
    }


    //Toast.makeText(LocalContext.current, countryName.size, Toast.LENGTH_SHORT).show()
    Box(Modifier.fillMaxSize(),contentAlignment = Alignment.Center){
        Column(Modifier.padding(top = 20.dp), verticalArrangement = Arrangement.spacedBy(20.dp)){
            Text("Create Touristic Point", fontSize = 30.sp, fontWeight = FontWeight.Bold)
            TextField(value = viewModel.state.pointId.toString(), onValueChange = {viewModel.changeId(it)}, placeholder = {Text("Id")}, keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                singleLine = true)
            TextField(value = viewModel.state.pointName, onValueChange = {viewModel.changeName(it)}, placeholder = {Text("Name")},
                singleLine = true)
            TextField(value = viewModel.state.pointPrice, onValueChange = {viewModel.changePrice(it)}, placeholder = {Text("Price")}, keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                singleLine = true)

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
                    countryOptions.forEach { selectedOption ->
                        DropdownMenuItem(onClick = {selectedCountry = selectedOption; expandedCountry = false; viewModel.changeCountry(selectedCountry); selectedCity = "-"}) {
                            Text(text= selectedOption)
                        }
                    }
                }
            }
            Row(){
                TextField(modifier = Modifier.width(130.dp),value = viewModel.state.latitude, onValueChange = {viewModel.changeLatitude(it)}, placeholder = {Text("Latitude")}, keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    singleLine = true)
                TextField(modifier = Modifier.width(150.dp).padding(start = 20.dp),value = viewModel.state.longitude, onValueChange = {viewModel.changeLongitude(it)}, placeholder = {Text("Longitude")}, keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    singleLine = true)
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
                ExposedDropdownMenu(expanded = expandedCity, onDismissRequest = {expandedCity = false},

                ) {
                    cityOptions.forEach { selectedOption ->
                        DropdownMenuItem(onClick = {selectedCity = selectedOption; expandedCity = false; viewModel.changeCity(selectedCity)}, enabled = selectedCountry != "-") {
                            Text(text= selectedOption)
                        }
                    }
                }
            }
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)){
                Button(onClick = { navController.navigate("home"); viewModel.cleanState()}){
                    Text("Go back")
                }
                Button(onClick = {viewModel.createProduct(); viewModel.cleanState()}, enabled = ((state.country!="-" && state.city!="-") && !viewModel.isEmptySpace())){
                    Text("Save")
                }
            }
        }

    }
}