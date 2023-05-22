package com.mkiperszmid.emptyapp.home.add_screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.mkiperszmid.emptyapp.viewModels.CountryViewModel

@Composable
fun AddCountry(viewModel: CountryViewModel, navController: NavHostController) {
    val state = viewModel.state
    Box(Modifier.fillMaxSize(),contentAlignment = Alignment.Center){
        Column(Modifier.padding(top = 20.dp), verticalArrangement = Arrangement.spacedBy(20.dp), horizontalAlignment = Alignment.CenterHorizontally){
            Text("Create Country", fontSize = 30.sp, fontWeight = FontWeight.Bold)
            TextField(value = state.code, onValueChange = {var a = it.uppercase(); if(a.length>3) a = a.substring(0,3);viewModel.changeCode(a);}, placeholder = {Text("Code")})
            TextField(value = state.name, onValueChange = {it -> viewModel.changeName(it)}, placeholder = {Text("Name")})
            TextField(value = state.lifeExpectancy.toString(), onValueChange = {it -> viewModel.changeLifeExpectancy(it)}, placeholder = {Text("Life expectancy")}
                , keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number))
            TextField(value = state.region, onValueChange = {it -> viewModel.changeRegion(it)}, placeholder = {Text("Region")})
            TextField(value = state.continent, onValueChange = {it -> viewModel.changeContinent(it)}, placeholder = {Text("Continent")})
            TextField(value = state.governmentForm, onValueChange = {it -> viewModel.changeGovernmentForm(it)}, placeholder = {Text("Government form")})
            Row(){
                Button(onClick = { navController.navigate("countryScreen"); viewModel.cleanState()}){
                    Text("Go back")
                }
                Button(onClick = {viewModel.createProduct(); viewModel.cleanState()}){
                    Text("Save")
                }
            }
        }

    }
}