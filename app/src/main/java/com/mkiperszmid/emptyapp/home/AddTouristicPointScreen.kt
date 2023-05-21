package com.mkiperszmid.emptyapp.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController

@Composable
fun AddTouristicPointScreen(viewModel: HomeViewModel, navController: NavHostController) {
    val state = viewModel.state
    Box(Modifier.fillMaxSize(),contentAlignment = Alignment.CenterStart){
        Column(Modifier.padding(top = 20.dp)){
            TextField(value = viewModel.state.pointId.toString(), onValueChange = {it -> viewModel.changeId(it.toInt())}, placeholder = {Text("Id")}, keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number))
            TextField(value = viewModel.state.pointName, onValueChange = {it -> viewModel.changeName(it)}, placeholder = {Text("Name")})
            TextField(value = viewModel.state.country, onValueChange = {it -> viewModel.changeCountry(it)}, placeholder = {Text("Country")})
            TextField(value = viewModel.state.city, onValueChange = {it -> viewModel.changeCity(it)}, placeholder = {Text("City")})
            TextField(value = viewModel.state.pointPrice.toString(), onValueChange = {it -> viewModel.changePrice(it.toDouble())}, placeholder = {Text("Price")}, keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number))
            Row(){
                Button(onClick = { navController.navigate("home"); viewModel.cleanState()}){
                    Text("Go back")
                }
                Button(onClick = {viewModel.createProduct()}){
                    Text("Save")
                }
            }
        }

    }
}