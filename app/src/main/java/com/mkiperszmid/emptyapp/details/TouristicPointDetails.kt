package com.mkiperszmid.emptyapp.details

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mkiperszmid.emptyapp.home.AddTouristicPointScreen
import com.mkiperszmid.emptyapp.home.HomeScreen
import com.mkiperszmid.emptyapp.viewModels.CountryViewModel
import com.mkiperszmid.emptyapp.viewModels.TouristicPointViewModel

@Composable
fun TouristicPointDetails (viewModel: TouristicPointViewModel, countryViewModel: CountryViewModel, navController: NavController){
    val state =  viewModel.state
    countryViewModel.updateCountry(state.country)
    Column(Modifier.fillMaxSize().padding(top = 20.dp, start = 10.dp, end =10.dp)) {
        BigText(state.pointName,"Name")
        BigText(state.city,"City")
        BigText(state.country, "Country", onClick = {navController.navigate("countryDetails")})
        BigText(state.pointPrice.toString(), "Price")
        BigText(state.latitude + ","+state.longitude, "GPS Coordinates")
        Button(onClick = {navController.navigate("home")}){
            Text("Go Back")
        }
    }
}
@Composable
fun BigText(text:String, type:String, onClick: () ->Unit = {}){
    Card(
        Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp).clickable { onClick() },elevation = 10.dp){
        Column(Modifier.padding(10.dp)){
            Row(){
                Text(type, fontSize = 20.sp)
            }
            Row(){
                Text(text, fontSize = 14.sp)
            }
        }
    }
}