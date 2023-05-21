package com.mkiperszmid.emptyapp.home

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    navController: NavController
) {
    val state = viewModel.state

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
            value = state.productPrice,
            onValueChange = { viewModel.changePrice(it) },
            placeholder = { Text(text = "Precio") }
        )
        Button(onClick = { viewModel.createProduct() }) {
            Text(text = "Agregar Producto")
        }*/
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(state.touristicPoints) {
                ProductItem(touristicPoint = it, modifier = Modifier.fillMaxWidth(), onEdit = {
                    viewModel.editProduct(it)
                }, onDelete = {
                    viewModel.deleteProduct(it)
                })
            }
        }
    }
        FloatingActionButton(
            modifier = Modifier
                .padding(all = 20.dp)
                .size(80.dp)
                .align(alignment = Alignment.BottomEnd),
            onClick = {
                      navController.navigate("test")
            },
            backgroundColor = Color(99, 5, 220)
        ) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "Back", Modifier.size(30.dp))
        }
}
}
