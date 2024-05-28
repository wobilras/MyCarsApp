package com.example.mycarsapp.data


data class Car(
    val id: Int? = 0,
    val name: String? = "",
    val imageResId: String? = "",
    val hourlyRate: Float? = 0f,
    val distance: String? = "",
    val licensePlate: String? = "",
    val fuelLevel: Int? = 0
)

data class User(
    val uid: String? = "",
    val userName: String? = "",
    val userPhotoResId: Int? = 2130968608,
    val userRating: Int? = 0,
    val tripsCompleted: Int? = 0,
    val fine: Int? = 0
)

enum class SearchStatus {
    LOADING,
    SUCCESS,
    ERROR,
    ERROR_NOT_FOUND
}

/*val carList = listOf(
    Car(1,"Toyota Camry", R.drawable.ic_launcher_foreground, 20.0f, "1 km", "B007OP87",100),
    Car(2,"Honda Civic", R.drawable.ic_launcher_foreground, 18.5f, "2 km","B007OP87", 95),
    Car(3,"BMW 3 Series", R.drawable.ic_launcher_foreground, 25.0f, "3 km","B007OP87", 40),
    Car(4,"Tesla Model 3", R.drawable.ic_launcher_foreground, 30.0f, "4 km","B007OP87", 50),
    Car(5,"Tesla Model 3", R.drawable.ic_launcher_foreground, 30.0f, "4 km","B007OP87", 70)
)*/
