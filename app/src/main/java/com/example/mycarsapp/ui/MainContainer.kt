package com.example.mycarsapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mycarsapp.R
import com.example.mycarsapp.data.carList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContainer() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    Scaffold(
        bottomBar = {
            if (navBackStackEntry?.destination?.route !in listOf(
                    "loginScreen",
                    "registrationScreen",
                    "endOfRent"
                )
            ) {
                BottomAppBar(containerColor = Color.Black) {
                    Button(
                        onClick = { navController.navigate("carListScreen") },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Black,
                            contentColor = Color.White
                        )
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(Icons.Filled.List, contentDescription = "Login")
                            Text(text = "Список")
                        }
                    }
                    Spacer(Modifier.weight(1f, true))
                    Button(
                        onClick = { navController.navigate("mapScreen") },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Black,
                            contentColor = Color.White
                        )
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                painterResource(id = R.drawable.map),
                                contentDescription = "Main"
                            )
                            Text(text = "Карта")
                        }
                    }
                    Spacer(Modifier.weight(1f, true))
                    Button(
                        onClick = { navController.navigate("accountScreen") },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Black,
                            contentColor = Color.White
                        )
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(Icons.Filled.Person, contentDescription = "Main")
                            Text(text = "Профиль")
                        }
                    }
                }
            }
        }
    ) { innerPadding ->
        val modifier = Modifier.padding(innerPadding)

        NavHost(
            navController = navController,
            startDestination = "loginScreen"
        ) {
            composable("loginScreen") {
                LoginScreen(navController)
            }
            composable("accountScreen") {
                AccountScreen(
                    userName = "Jone Brit",
                    userPhotoResId = R.drawable.ic_launcher_foreground,
                    userRating = 5,
                    tripsCompleted = 12,
                    fine = 1
                ) {
                    //navController.navigate("imageDownloadScreen")
                }
            }
            composable("carListScreen") {
                CarListScreen { car ->
                    navController.navigate("carInfoScreen/${car.name}")
                }
            }
            composable("registrationScreen"){
                RegistrationScreen(navController)
            }
            composable("mapScreen"){
                MapScreen()
            }
            composable(
                "carInfoScreen/{carName}",
                arguments = listOf(navArgument("carName") { type = NavType.StringType })
            ) { backStackEntry ->
                val carName = backStackEntry.arguments?.getString("carName") ?: ""
                val car = carList.find { it.name == carName } ?: carList.first()
                CarInfoScreen(car = car, navController)
            }
            composable("carRent"){
                CarRent(carName = "Civ", licensePlate = "B004KO69", fuelLevel = 100, bookingTime = "23 mins", navController) {}
            }
            composable("endOfRent"){
                EndOfRent(navController)
            }
        }
    }
}