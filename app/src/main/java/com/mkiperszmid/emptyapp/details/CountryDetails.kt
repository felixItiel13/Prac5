package com.mkiperszmid.emptyapp.details

import android.widget.Toast
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
import com.mkiperszmid.emptyapp.viewModels.CountryViewModel

@Composable
fun CountryDetails (viewModel: CountryViewModel, navController: NavController){
    val state =  viewModel.state
    Toast.makeText(LocalContext.current,"Entered in country details", Toast.LENGTH_SHORT).show()
    Column(Modifier.fillMaxSize().padding(top = 20.dp, start = 10.dp, end =10.dp)) {
        BigText(text = state.name,"Name")
        BigText("-"+state.code+"-","Code")
        BigText(state.governmentForm, "Type of Government")
        BigText(state.region, "Region")
        BigText(text = state.continent, type = "Continent")
        Button(onClick = {
            navController.navigate(navController.backQueue[navController.backQueue.size-2].destination.route!!)
        }
        ){
            Text("Go Back")
        }
    }
}