package com.mkiperszmid.emptyapp.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController

@Composable
fun AddTouristicPointScreen(viewModel: HomeViewModel, navController: NavHostController) {
    val state = viewModel.state
    var name by remember{ mutableStateOf("") }
    var country by remember{ mutableStateOf("") }
    var city by remember{ mutableStateOf("") }
    var price by remember{ mutableStateOf("") }
    Box(Modifier.fillMaxSize(),contentAlignment = Alignment.CenterStart){
        Column(Modifier.padding(top = 20.dp)){
            TextField(value = "", onValueChange = {it -> name = it}, placeholder = {Text("Name")})
            TextField(value = "", onValueChange = {it -> country = it}, placeholder = {Text("Country")})
            TextField(value = "", onValueChange = {it -> city = it}, placeholder = {Text("City")})
            TextField(value = "", onValueChange = {it -> price = it}, placeholder = {Text("Price")})
            Row(){
                Button(onClick = { navController.navigate("home")}){
                    Text("go back")
                }
                Button(onClick = {}){
                    Text("go back")
                }
            }
        }

    }
}