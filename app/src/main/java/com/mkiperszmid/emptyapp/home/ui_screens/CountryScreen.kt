package com.mkiperszmid.emptyapp.home.ui_screens

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mkiperszmid.emptyapp.home.CountryItem
import com.mkiperszmid.emptyapp.home.ProductItem
import com.mkiperszmid.emptyapp.viewModels.CountryViewModel
import com.mkiperszmid.emptyapp.viewModels.FilterViewModel
import com.mkiperszmid.emptyapp.viewModels.TouristicPointViewModel

@Composable
fun CountryScreen(
    countryViewModel: CountryViewModel,
    navController: NavController
) {
    val countryState = countryViewModel.state
    Box(Modifier.fillMaxSize()){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp, start = 10.dp, end = 10.dp).fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(7.dp))
        {
            Row(verticalAlignment = Alignment.CenterVertically){
            Text("Registered Countries", fontWeight = FontWeight.Bold, fontSize = 24.sp )
        }
            LazyColumn(Modifier.height(630.dp)){
                items(countryState.countryList) {
                    CountryItem(country = it, modifier = Modifier.fillMaxWidth(), onDelete = {},
                        navController = navController, countryViewModel = countryViewModel)
                }
            }
            Button(onClick = {navController.navigate("home")}){
                Text("Go Back")
            }
        }
        FloatingActionButton(
            modifier = Modifier
                .padding(all = 20.dp)
                .size(70.dp)
                .align(alignment = Alignment.BottomEnd),
            onClick = {
                navController.navigate("addCountry")
            },
            backgroundColor = Color(99, 5, 220)
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add", Modifier.size(30.dp))
        }

        }
    }
