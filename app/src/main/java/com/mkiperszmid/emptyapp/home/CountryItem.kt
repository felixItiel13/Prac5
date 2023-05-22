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
import com.mkiperszmid.emptyapp.entities.Country
import com.mkiperszmid.emptyapp.viewModels.CountryViewModel


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CountryItem(
    country: Country,
    countryViewModel: CountryViewModel,
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

            countryViewModel.changeName(country.name)
            countryViewModel.changeRegion(country.region)
            countryViewModel.changeContinent(country.continent)
            countryViewModel.changeLifeExpectancy(country.lifeExpectancy.toString())
            countryViewModel.changeGovernmentForm(country.governmentForm)
            countryViewModel.changeCode(country.code)
            navController.navigate("countryDetails")
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
                            Text(text = "Country name", color = Color.LightGray, fontSize = 10.sp)
                            Text(country.name, fontSize = 18.sp)
                        }
                    }
                    Row {
                        Column(Modifier.padding(top = 5.dp, bottom = 0.dp, end= 30.dp).width(90.dp)) {
                            Text(
                                text = "Region",
                                color = Color.LightGray,
                                fontSize = 10.sp
                            )
                            Text(country.region, fontSize = 18.sp)
                        }
                        Column(Modifier.padding(top = 5.dp, bottom = 10.dp).width(100.dp)) {
                            Text(
                                text = "Government Form",
                                color = Color.LightGray,
                                fontSize = 10.sp
                            )
                            Text(
                                country.governmentForm,
                                fontSize = 18.sp
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