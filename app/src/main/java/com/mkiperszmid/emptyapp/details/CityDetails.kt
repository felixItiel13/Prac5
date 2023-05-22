package com.mkiperszmid.emptyapp.details

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.mkiperszmid.emptyapp.viewModels.CityViewModel

@Composable
fun CityDetails(viewModel: CityViewModel, navController: NavController) {
    val state =  viewModel.state
    Toast.makeText(LocalContext.current,"Entered in country details", Toast.LENGTH_SHORT).show()
    Column(Modifier.fillMaxSize()) {
        BigText(text = state.id.toString(),"ID")
        BigText(text = state.name,"Name")
        BigText(state.countryCode,"Code")
        BigText(state.population, "Population")
        Button(onClick = {
            navController.navigate(navController.backQueue[navController.backQueue.size-2].destination.route!!)
        }
        ){
            Text("Go Back")
        }
    }
}