package com.mkiperszmid.emptyapp.home.ui_screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mkiperszmid.emptyapp.home.LanguageItem
import com.mkiperszmid.emptyapp.viewModels.CountryViewModel
import com.mkiperszmid.emptyapp.viewModels.LanguageViewModel

@Composable
fun LanguageScreen(languageViewModel: LanguageViewModel, navController: NavController) {
    val languageState = languageViewModel.state
    Box(Modifier.fillMaxSize()){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp, start = 10.dp, end = 10.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp))
        {
            Row(verticalAlignment = Alignment.CenterVertically){
                Text("Registered Languages", fontWeight = FontWeight.Bold, fontSize = 24.sp )
            }
            LazyColumn(
                Modifier
                    .height(630.dp)
                    .fillMaxWidth()) {
                items(languageState.languageList) {
                    LanguageItem(
                        language = it,
                        languageViewModel = languageViewModel,
                    )
                }
            }
            Button(onClick = {
                navController.navigate("home")
            }){
                Text("Go Back")
            }
        }
        FloatingActionButton(
            modifier = Modifier
                .padding(all = 20.dp)
                .size(70.dp)
                .align(Alignment.BottomEnd)
            ,
            onClick = {
                navController.navigate("addLanguage")
            },
            backgroundColor = Color(99, 5, 220)
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add", Modifier.size(30.dp))
        }

    }
}