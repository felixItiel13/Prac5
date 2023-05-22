package com.mkiperszmid.emptyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.mkiperszmid.emptyapp.details.CityDetails
import com.mkiperszmid.emptyapp.details.CountryDetails
import com.mkiperszmid.emptyapp.details.FilterScreen
import com.mkiperszmid.emptyapp.details.TouristicPointDetails
import com.mkiperszmid.emptyapp.home.add_screens.AddCountry
import com.mkiperszmid.emptyapp.home.AddTouristicPointScreen
import com.mkiperszmid.emptyapp.home.HomeScreen
import com.mkiperszmid.emptyapp.home.ProductDatabase
import com.mkiperszmid.emptyapp.home.add_screens.AddCity
import com.mkiperszmid.emptyapp.home.add_screens.AddLanguage
import com.mkiperszmid.emptyapp.home.ui_screens.CityScreen
import com.mkiperszmid.emptyapp.home.ui_screens.CountryScreen
import com.mkiperszmid.emptyapp.home.ui_screens.LanguageScreen
import com.mkiperszmid.emptyapp.ui.theme.EmptyAppTheme
import com.mkiperszmid.emptyapp.viewModels.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EmptyAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val database =
                        Room.databaseBuilder(this, ProductDatabase::class.java, "product_db")
                            .build()
                    val dao = database.dao
                    val viewModel by viewModels<TouristicPointViewModel>(factoryProducer = {
                        object : ViewModelProvider.Factory {
                            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                                return TouristicPointViewModel(dao) as T
                            }
                        }
                    })
                    val countryViewModel by viewModels<CountryViewModel>(factoryProducer = {
                        object : ViewModelProvider.Factory {
                            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                                return CountryViewModel(database.countryDao) as T
                            }
                        }
                    })

                    val cityViewModel by viewModels<CityViewModel>(factoryProducer = {
                        object : ViewModelProvider.Factory {
                            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                                return CityViewModel(database.cityDao) as T
                            }
                        }
                    })

                    val filterViewModel by viewModels<FilterViewModel>(factoryProducer = {
                        object : ViewModelProvider.Factory {
                            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                                return FilterViewModel() as T
                            }
                        }
                    })
                    val languageViewModel by viewModels<LanguageViewModel>(factoryProducer = {
                        object : ViewModelProvider.Factory {
                            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                                return LanguageViewModel(database.languageDao) as T
                            }
                        }
                    })

                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "home"){
                        composable("home"){
                            HomeScreen(viewModel = viewModel, filterViewModel = filterViewModel, countryViewModel = countryViewModel, navController = navController)
                        }
                        composable("countryScreen"){
                            CountryScreen(countryViewModel = countryViewModel, navController = navController)
                        }
                        composable("cityScreen"){
                            CityScreen(
                                cityViewModel = cityViewModel,
                                navController = navController
                            )
                        }
                        composable("languageScreen"){
                            LanguageScreen(languageViewModel = languageViewModel,navController = navController )
                        }


                        composable("addTouristicPoint"){
                            AddTouristicPointScreen(viewModel = viewModel, countryViewModel,cityViewModel,navController)
                        }
                        composable("addCountry"){
                            AddCountry(viewModel = countryViewModel,navController)
                        }
                        composable("addCity"){
                            AddCity(viewModel = cityViewModel, countryViewModel,navController)
                        }
                        composable("addLanguage"){
                            AddLanguage(viewModel = languageViewModel,countryViewModel, navController =navController )
                        }



                        composable("touristicPointDetails"){
                            TouristicPointDetails(viewModel = viewModel, countryViewModel,  navController)
                        }
                        composable("countryDetails"){
                            CountryDetails(viewModel = countryViewModel, navController = navController )
                        }
                        composable("cityDetails"){
                            CityDetails(viewModel = cityViewModel, navController = navController )
                        }
                        composable("filterScreen"){
                            FilterScreen(filterViewModel = filterViewModel,
                                countryViewModel = countryViewModel,
                                cityViewModel = cityViewModel,
                                navController = navController )
                        }
                    }
                }
            }
        }
    }
}
