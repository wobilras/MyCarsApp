package com.example.mycarsapp.data

import com.example.mycarsapp.R

data class Car(
    val name: String,
    val imageResId: Int,
    val hourlyRate: Float,
    val distance: String,
    val licensePlate: String,
    val fuelLevel: Int
)

val carList = listOf(
    Car("Toyota Camry", R.drawable.ic_launcher_foreground, 20.0f, "1 km", "B007OP87",100),
    Car("Honda Civic", R.drawable.ic_launcher_foreground, 18.5f, "2 km","B007OP87", 95),
    Car("BMW 3 Series", R.drawable.ic_launcher_foreground, 25.0f, "3 km","B007OP87", 40),
    Car("Tesla Model 3", R.drawable.ic_launcher_foreground, 30.0f, "4 km","B007OP87", 50),
    Car("Tesla Model 3", R.drawable.ic_launcher_foreground, 30.0f, "4 km","B007OP87", 70)
)
