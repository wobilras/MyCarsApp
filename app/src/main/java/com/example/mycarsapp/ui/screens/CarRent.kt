package com.example.mycarsapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mycarsapp.R
import com.example.mycarsapp.data.Car
import com.example.mycarsapp.ui.theme.White
import com.example.mycarsapp.ui.theme.mediumText
import kotlinx.coroutines.launch

/*
@Composable
fun CarRent(
    carName: String,
    licensePlate: String,
    fuelLevel: Int,
    bookingTime: String,
    navController: NavController,
    onDismiss: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = carName, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                Text(text = licensePlate, fontWeight = FontWeight.Bold, fontSize = 15.sp)
            }
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp, top = 16.dp),
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Car Image"
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Fuel Level: $fuelLevel%",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 8.dp))
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.Black)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = bookingTime,
                            color = Color.White,
                            modifier = Modifier.padding(10.dp),
                            fontWeight = FontWeight.Bold
                        )
                        Button(onClick = { */
/*TODO*//*
 },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 30.dp, end = 30.dp,
                                    top = 5.dp, bottom = 5.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White,
                                contentColor = Color.Black
                            )
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.volume),
                                contentDescription = "Signal",
                                )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = "Звук")
                        }
                        Button(onClick = { */
/*TODO*//*
 },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 30.dp, end = 30.dp,
                                    top = 5.dp, bottom = 5.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White,
                                contentColor = Color.Black
                            )
                        ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.lock),
                                    contentDescription = "Lock/Unlock"
                                )
                                Text(text = "Закрыть/открыть авто")
                        }
                        Button(onClick = { navController.navigate("endOfRent") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 30.dp, end = 30.dp,
                                    top = 5.dp, bottom = 5.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White,
                                contentColor = Color.Black
                            )
                        ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.finish),
                                    contentDescription = "Finish Trip"
                                )
                                Text(text ="Завершить поездку")
                        }
                    }
                }
            }
        }
    }
}*/

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CarRent(car: Car, navController: NavController) {
    val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Expanded)
    val coroutineScope = rememberCoroutineScope()

    ModalBottomSheetLayout(
        sheetContent = {
            when (sheetState.currentValue) {
                ModalBottomSheetValue.HalfExpanded -> {
                    // Содержимое для свернутого состояния
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .background(Color.White)
                            .clickable {
                                coroutineScope.launch {
                                    sheetState.show()
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                        ) {
                            Text(text = car.name!!, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                            Text(text = car.licensePlate!!, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                        }
                    }
                }
                else -> {
                    // Полное содержимое
                    CarRentContent(
                        car = car,
                        onDismiss = {
                            coroutineScope.launch {
                                //sheetState.hide()
                            }
                        },
                        navController = navController
                    )
                }
            }
        },
        sheetState = sheetState
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
        ) {
            MapScreen(navController = navController)
        }
    }
}

@Composable
fun CarRentContent(car: Car, onDismiss: () -> Unit, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "${car.name}", color = MaterialTheme.colorScheme.secondary,
                fontWeight = FontWeight.Bold, fontSize = mediumText)
            Text(text = "${car.licensePlate}", color = MaterialTheme.colorScheme.secondary,
                fontWeight = FontWeight.Bold, fontSize = mediumText)
        }
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp, top = 16.dp),
            painter = painterResource(id = car.imageResId!!),
            contentDescription = "${car.imageResId}"
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.fuelLvl) +" ${car.fuelLevel}%",
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.Black)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.booking_time)+ " 00:01:00 ",
                        color = White,
                        modifier = Modifier.padding(10.dp),
                        fontWeight = FontWeight.Bold
                    )
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 30.dp, end = 30.dp, top = 5.dp, bottom = 5.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color.Black
                        )
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.volume),
                            contentDescription = "Signal",
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = stringResource(id = R.string.volume))
                    }
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 30.dp, end = 30.dp, top = 5.dp, bottom = 5.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color.Black
                        )
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.lock),
                            contentDescription = "Lock/Unlock"
                        )
                        Text(text = stringResource(id = R.string.Lock_Unlock))
                    }
                    Button(
                        onClick = {
                            onDismiss()
                            navController.navigate("endOfRent/${car.id}")
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 30.dp, end = 30.dp, top = 5.dp, bottom = 5.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color.Black
                        )
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.finish),
                            contentDescription = "Finish Trip"
                        )
                        Text(text = stringResource(id = R.string.Finish_trip))
                    }
                }
            }
        }
    }
}