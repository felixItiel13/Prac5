package com.mkiperszmid.emptyapp.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mkiperszmid.emptyapp.entities.City
import com.mkiperszmid.emptyapp.viewModels.CityViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CityItem(
    city: City,
    cityViewModel: CityViewModel,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier,
    navController: NavController
) {

    Row(
        Modifier
            .padding(start = 10.dp, end = 10.dp)
            .padding(vertical = 5.dp)
    ) {
        Card(elevation = 10.dp, onClick = {

            cityViewModel.changeName(city.name)
            cityViewModel.changeCountryCode(city.countryCode)
            cityViewModel.changePopulation(city.population.toString())
            navController.navigate("cityDetails")
        }) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Row() {
                        Column(Modifier.padding(top = 10.dp, bottom = 0.dp)) {
                            Text(text = "City name", color = Color.LightGray, fontSize = 10.sp)
                            Text(city.name, fontSize = 20.sp)
                        }
                    }
                    Row {
                        Column(Modifier.padding(top = 5.dp, bottom = 0.dp, end= 30.dp)) {
                            Text(
                                text = "Country Code",
                                color = Color.LightGray,
                                fontSize = 10.sp
                            )
                            Text(city.countryCode, fontSize = 20.sp)
                        }
                        Column(Modifier.padding(top = 5.dp, bottom = 10.dp)) {
                            Text(
                                text = "Population",
                                color = Color.LightGray,
                                fontSize = 10.sp
                            )
                            Text(
                                city.population.toString(),
                                fontSize = 20.sp
                            )
                        }
                    }
                }
                /* Column() {
                        IconButton(onClick = onEdit) {
                            Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit")
                        }
                    }*/
                Column() {
                    IconButton(onClick = onDelete) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")
                    }
                }
            }
        }
    }
}