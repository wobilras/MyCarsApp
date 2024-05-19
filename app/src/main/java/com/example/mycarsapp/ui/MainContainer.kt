package com.example.mycarsapp.ui

import androidx.compose.foundation.layout.Box
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
import com.example.mycarsapp.data.getUser
import com.example.mycarsapp.data.user
import com.example.mycarsapp.ui.screens.AccountScreen
import com.example.mycarsapp.ui.screens.CarInfoScreen
import com.example.mycarsapp.ui.screens.CarListScreen
import com.example.mycarsapp.ui.screens.CarRent
import com.example.mycarsapp.ui.screens.EndOfRent
import com.example.mycarsapp.ui.screens.LoginScreen
import com.example.mycarsapp.ui.screens.MapScreen
import com.example.mycarsapp.ui.screens.RegistrationScreen
import com.example.mycarsapp.ui.screens.SettingsScreen
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContainer() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val startDest:String = if (FirebaseAuth.getInstance().currentUser!= null){
        "mapScreen"
    }
    else "loginScreen"

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
                        onClick = {
                            CoroutineScope(Dispatchers.IO).launch {
                                user = getUser(FirebaseAuth.getInstance().currentUser!!.uid)
                            }
                            navController.navigate("accountScreen") },
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
        Box(modifier = Modifier.padding(innerPadding)) {
            NavHost(
                navController = navController,
                startDestination = startDest
            //"loginScreen"
            ) {
                FirebaseAuth.getInstance().currentUser
                composable("loginScreen") {
                    LoginScreen(navController)
                }
                composable("accountScreen") {
                    AccountScreen(
                        user,
                        navController = navController
                    ) {}
                }
                composable("carListScreen") {
                    CarListScreen { car ->
                        navController.navigate("carInfoScreen/${car.name}")
                    }
                }
                composable("registrationScreen") {
                    RegistrationScreen(navController)
                }
                composable("mapScreen") {
                    MapScreen(navController)
                }
                composable(
                    "carInfoScreen/{carName}",
                    arguments = listOf(navArgument("carName") { type = NavType.StringType })
                ) { backStackEntry ->
                    val carName = backStackEntry.arguments?.getString("carName") ?: ""
                    val car = carList.find { it.name == carName } ?: carList.first()
                    CarInfoScreen(car = car, navController)
                }
                composable(
                    "carRent/{carId}",
                    arguments = listOf(navArgument("carId") { type = NavType.IntType })
                ) { backStackEntry ->
                    val carId = backStackEntry.arguments?.getInt("carId") ?: 0
                    val car = carList.find { it.id == carId } ?: carList.first()
                    CarRent(car = car, navController)
                }
                composable(
                    "endOfRent/{carId}",
                    arguments = listOf(navArgument("carId") { type = NavType.IntType })
                ) { backStackEntry ->
                    val carId = backStackEntry.arguments?.getInt("carId") ?: 0
                    EndOfRent(carId = carId.toString(), navController = navController)
                }
                composable("settingsScreen") {
                    SettingsScreen()
                }
            }
        }
    }
}