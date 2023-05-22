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
import com.mkiperszmid.emptyapp.entities.Language
import com.mkiperszmid.emptyapp.viewModels.LanguageViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun LanguageItem(
    language: Language,
    languageViewModel: LanguageViewModel,
    modifier: Modifier = Modifier,
) {

    Row(
        Modifier
            .padding(start = 10.dp, end = 10.dp)
            .padding(vertical = 5.dp)
    ) {
        Card(elevation = 10.dp, onClick = {

            languageViewModel.changeName(language.name)
            languageViewModel.changeIsOfficial(language.isOfficial.toString())
            languageViewModel.changeCountryCode(language.countryCode)
        }) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Row(horizontalArrangement = Arrangement.SpaceAround,modifier = Modifier.padding(top = 10.dp, bottom = 10.dp)) {
                        Column(Modifier.width(80.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "Country code", color = Color.LightGray, fontSize = 10.sp)
                            Text(language.countryCode, fontSize = 18.sp)
                        }
                        Column(Modifier.width(130.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "Language name", color = Color.LightGray, fontSize = 10.sp)
                            Text(language.name, fontSize = 18.sp)
                        }
                        Column(Modifier.width(60.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "isOfficial", color = Color.LightGray, fontSize = 10.sp)
                            Text(language.isOfficial.toString(), fontSize = 18.sp)
                        }
                    }

                }
                /* Column() {
                        IconButton(onClick = onEdit) {
                            Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit")
                        }
                    }*/
            }
        }
    }
}