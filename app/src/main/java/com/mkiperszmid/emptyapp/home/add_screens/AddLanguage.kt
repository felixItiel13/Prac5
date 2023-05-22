package com.mkiperszmid.emptyapp.home.add_screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.mkiperszmid.emptyapp.viewModels.CountryViewModel
import com.mkiperszmid.emptyapp.viewModels.LanguageViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddLanguage(viewModel: LanguageViewModel, countryViewModel:CountryViewModel, navController: NavHostController) {
    val state = viewModel.state
    var checkedState by remember { mutableStateOf(false) }
    viewModel.changeIsOfficial(checkedState.toString())


    val countryState = countryViewModel.state

    val countryCodes = countryState.countryList.map { it -> it.code }
    val countryCodesList = countryCodes.toMutableList()
    countryCodesList.add(0,"-")

    var selectedCountry by remember { mutableStateOf(countryCodesList[0])}
    viewModel.changeCountryCode(selectedCountry)

    var expandedCountry by remember { mutableStateOf(false) }


    Box(Modifier.fillMaxSize(),contentAlignment = Alignment.Center){
        Column(Modifier.padding(top = 20.dp), verticalArrangement = Arrangement.spacedBy(20.dp), horizontalAlignment = Alignment.CenterHorizontally){
            Text("Create Language", fontSize = 30.sp, fontWeight = FontWeight.Bold)

            ExposedDropdownMenuBox(expanded = expandedCountry , onExpandedChange = {expandedCountry = !expandedCountry}) {
                TextField(value = selectedCountry,
                    onValueChange = {},
                    readOnly = true,
                    label = {Text("Country code")},
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedCountry)
                    },
                    colors = ExposedDropdownMenuDefaults.textFieldColors()
                )
                ExposedDropdownMenu(expanded = expandedCountry, onDismissRequest = {expandedCountry = false}

                ) {
                    countryCodesList.forEach { selectedOption ->
                        DropdownMenuItem(onClick = {selectedCountry = selectedOption; expandedCountry = false; viewModel.changeCountryCode(selectedCountry)}) {
                            Text(text= selectedOption)
                        }
                    }
                }
            }



            //TextField(value = state.countryCode, onValueChange = {var a = it.uppercase(); if(a.length>3) a = a.substring(0,3);viewModel.changeCountryCode(a);}, placeholder = { Text("Code") })
            TextField(value = state.name, onValueChange = {it -> viewModel.changeName(it)}, placeholder = { Text("Name") })
            Checkbox(
                checked = checkedState,
                onCheckedChange = { checkedState= it ; viewModel.changeIsOfficial(checkedState.toString())}
            )
            Row(){
                Button(onClick = { navController.navigate("languageScreen"); viewModel.cleanState()}){
                    Text("Go back")
                }
                Button(onClick = {viewModel.createLanguage(); viewModel.cleanState()}){
                    Text("Save")
                }
            }
        }

    }
}