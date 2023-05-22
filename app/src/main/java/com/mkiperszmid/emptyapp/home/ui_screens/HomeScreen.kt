package com.mkiperszmid.emptyapp.home

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.LocationCity
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Info
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mkiperszmid.emptyapp.viewModels.CountryViewModel
import com.mkiperszmid.emptyapp.viewModels.FilterViewModel
import com.mkiperszmid.emptyapp.viewModels.TouristicPointViewModel

@Composable
fun HomeScreen(
    viewModel: TouristicPointViewModel,
    countryViewModel: CountryViewModel,
    filterViewModel: FilterViewModel,
    navController: NavController
) {

    var text by remember{ mutableStateOf("") }
    val state = viewModel.state
    val filterState = filterViewModel.state
    val countryState = countryViewModel.state

    if(filterState.active) {
        val filterCode = ""
        if (filterState.country != "") {
            countryViewModel.updateCountryByName(filterState.country)
        }
        viewModel.activeFilter(countryState.code, filterState.city, filterState.minPrice, filterState.maxPrice)
    }else{
        viewModel.disableFilter()
    }

    Box(){
        Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        /*Text(text = "Mis Productos", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
        TextField(
            value = state.pointName,
            onValueChange = { viewModel.changeName(it) },
            placeholder = { Text(text = "Nombre del producto") }
        )
        TextField(
            value = state.pointPrice,
            onValueChange = { viewModel.changePrice(it) },
            placeholder = { Text(text = "Precio") }
        )
        Button(onClick = { viewModel.createProduct() }) {
            Text(text = "Agregar Producto")
        }*/
            Row(verticalAlignment = Alignment.CenterVertically){
                IconButton(onClick = { navController.navigate("filterScreen")}, Modifier.padding(top =15.dp)) {
                    Icon(imageVector = Icons.Outlined.Check, contentDescription = "filter")
                }
                TextField(
                    value = text,
                    onValueChange = {text = it; viewModel.searchPoint(it)},
                    Modifier.padding(top = 15.dp),
                    placeholder = {
                        Text("Search")
                    }
                )
            }
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(state.touristicPoints) {
                ProductItem(touristicPoint = it, modifier = Modifier.fillMaxWidth(), onEdit = {
                    viewModel.editProduct(it)
                }, onDelete = {
                    viewModel.deleteProduct(it)
                }, onClick = {navController.navigate("pointInfo")},
                navController = navController, touristicPointViewModel = viewModel)
            }
        }
    }

        //Add turisticPoint button
        FloatingActionButton(
            modifier = Modifier
                .padding(all = 20.dp)
                .size(70.dp)
                .align(alignment = Alignment.BottomEnd),
            onClick = {viewModel.cleanState();
                      navController.navigate("addTouristicPoint")
            },
            backgroundColor = Color(99, 5, 220)
        ) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "Back", Modifier.size(30.dp))
        }
        //Show country tables
        FloatingActionButton(
                modifier = Modifier
                    .padding(all = 20.dp)
                    .padding(end = 80.dp, bottom = 9.dp)
                    .size(50.dp)
                    .align(alignment = Alignment.BottomEnd),
        onClick = {viewModel.cleanState();
            navController.navigate("countryScreen")
        },
        backgroundColor = Color(99, 5, 220)
        ) {
            Icon(imageVector = Icons.Default.Public, contentDescription = "Countries", Modifier.size(30.dp))
            //Text(text = "Countries")
        }
        //Show city tables
        FloatingActionButton(
            modifier = Modifier
                .padding(all = 20.dp)
                .padding(end = 160.dp, bottom = 9.dp)
                .size(50.dp)
                .align(alignment = Alignment.BottomEnd),
            onClick = {
                navController.navigate("cityScreen")
            },
            backgroundColor = Color(99, 5, 220)
        ) {
            Icon(imageVector = Icons.Default.LocationCity, contentDescription = "Cities", Modifier.size(30.dp))
            //Text("Cities")
        }
        //Show languages tables
        FloatingActionButton(
            modifier = Modifier
                .padding(all = 20.dp)
                .padding(end = 200.dp, bottom = 9.dp)
                .size(50.dp)
                .align(alignment = Alignment.BottomEnd),
            onClick = {
                navController.navigate("languageScreen")
            },
            backgroundColor = Color(99, 5, 220)
        ) {
            Icon(imageVector = Icons.Default.Translate, contentDescription = "Languages", Modifier.size(30.dp))
        }
}
}
