package com.example.mycarsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mycarsapp.data.carList
import com.example.mycarsapp.ui.CarInfoScreen
import com.example.mycarsapp.ui.CarListScreen
import com.example.mycarsapp.ui.EndOfRent
import com.example.mycarsapp.ui.LoginScreen
import com.example.mycarsapp.ui.MainContainer
import com.example.mycarsapp.ui.RegistrationScreen
import com.example.mycarsapp.ui.theme.MyCarsAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyCarsAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //CarListScreen{}
                    //LoginScreen()
                    //RegistrationScreen()
                    /*AccountScreen(
                        userName = "Jone",
                        userPhotoResId = R.drawable.ic_launcher_foreground,
                        userRating = 5,
                        tripsCompleted = 25,
                        fine = 1
                    ) {}*/
                    /*CarRent(
                        carName = "mazda miata",
                        licensePlate = "B755OP69",
                        fuelLevel = 95,
                        bookingTime = "5465") {}*/
                    MainContainer()
                    //CarInfoScreen(carList[1])
                    //EndOfRent()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyCarsAppTheme {
        //RegistrationScreen()
    }
}