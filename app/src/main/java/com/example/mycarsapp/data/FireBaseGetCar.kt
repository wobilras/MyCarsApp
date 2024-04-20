package com.example.mycarsapp.data

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

var carList: ArrayList<Car> = arrayListOf()
private lateinit var firebaseRef: DatabaseReference

fun firebaseGetCar(searchText: String, onDataLoaded: (ArrayList<Car>, SearchStatus) -> Unit) {
    firebaseRef = FirebaseDatabase.getInstance().getReference("Cars")
    firebaseRef.addValueEventListener(object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            carList.clear()
            if (snapshot.exists()) {
                for (carsSnap in snapshot.children) {
                    val car = carsSnap.getValue(Car::class.java)
                    if (car != null && car.name?.contains(searchText, ignoreCase = true) == true) {
                        carList.add(car)
                    }
                }
                if (carList.isEmpty()) {
                    onDataLoaded(carList, SearchStatus.ERROR_NOT_FOUND)
                } else {
                    onDataLoaded(carList, SearchStatus.SUCCESS)
                }
            } else {
                onDataLoaded(carList, SearchStatus.ERROR_NOT_FOUND)
            }
        }

        override fun onCancelled(error: DatabaseError) {
            onDataLoaded(carList, SearchStatus.ERROR)
            Log.e("DB ERROR", "Failed to get data from DB: ${error.message}")
        }

    })
}