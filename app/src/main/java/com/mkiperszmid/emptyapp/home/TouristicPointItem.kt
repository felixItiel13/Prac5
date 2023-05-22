package com.mkiperszmid.emptyapp.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mkiperszmid.emptyapp.entities.TouristicPoint
import com.mkiperszmid.emptyapp.viewModels.TouristicPointViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProductItem(
    touristicPoint: TouristicPoint,
    touristicPointViewModel: TouristicPointViewModel,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier,
    onClick: () ->Unit,
    navController: NavController
) {

    Row(
        Modifier
            .padding(start = 10.dp, end = 10.dp)
            .padding(vertical = 5.dp)
    ) {
        Card(elevation = 10.dp, onClick = {

            touristicPointViewModel.changeName(touristicPoint.name)
            touristicPointViewModel.changeCountry(touristicPoint.countryCode)
            touristicPointViewModel.changeCity(touristicPoint.city)
            touristicPointViewModel.changePrice(touristicPoint.price.toString())
            touristicPointViewModel.changeLatitude(touristicPoint.latitude.toString())
            touristicPointViewModel.changeLongitude(touristicPoint.longitude.toString())

            navController.navigate("touristicPointDetails")
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
                            Text(text = "Point name", color = Color.LightGray, fontSize = 10.sp)
                            Text(touristicPoint.name, fontSize = 20.sp)
                        }
                    }
                    Row {
                        Column(Modifier.padding(top = 5.dp, bottom = 0.dp, end= 30.dp)) {
                            Text(
                                text = "Price",
                                color = Color.LightGray,
                                fontSize = 10.sp
                            )
                            Text("$ ${touristicPoint.price}", fontSize = 20.sp)
                        }
                        Column(Modifier.padding(top = 5.dp, bottom = 10.dp)) {
                            Text(
                                text = "City",
                                color = androidx.compose.ui.graphics.Color.LightGray,
                                fontSize = 10.sp
                            )
                            Text(
                                "${touristicPoint.city}",
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


