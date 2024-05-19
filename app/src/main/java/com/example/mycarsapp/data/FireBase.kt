package com.example.mycarsapp.data

import android.content.Context
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateMapOf
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await

var carList: ArrayList<Car> = arrayListOf()
private lateinit var firebaseRef: DatabaseReference
var user = User()
val photosMap = mutableStateMapOf<String, MutableList<Uri>>()

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

suspend fun signUpUser(
    email: String,
    pass: String,
    passConfirm: String,
    login: String,
    context: Context
): Boolean {
    val uid = FirebaseAuth.getInstance()
    val ref = FirebaseDatabase.getInstance(
        "https://sharingapp-9ac78-default-rtdb.europe-west1.firebasedatabase.app/")
        .getReference("/Users")
    var isNoError = false

    if (email.isBlank() || pass.isBlank() || passConfirm.isBlank() || login.isBlank()) {
        showToast(context, "Заполните ВСЕ поля")
        return false
    }
    if (pass != passConfirm) {
        showToast(context, "Пароли не совпадают")
        return false
    }
    try {
        Firebase.auth.createUserWithEmailAndPassword(email, pass).await()
    } catch (e: FirebaseAuthException) {
        return when (e.errorCode) {
            "ERROR_INVALID_EMAIL" ->{
                showToast(context, "Неверный формат email")
                false
            }
            "ERROR_WEAK_PASSWORD" -> {
                showToast(context, "Слабый пароль")
                false
            }
            else -> {
                showToast(context, "Неизвестная ошибка")
                false
            }
        }
    } catch (e: Exception) {
        showToast(context, "Неизвестная ошибка")
        return false
    }
    val userId = uid.currentUser!!.uid
    val user = User(userId, login, 2130968608, 5, 0, 0)
    ref.child(userId).setValue(user)
        .addOnSuccessListener {
            showToast(context, "Успешная регистрация")
            isNoError = true
        }
        .addOnFailureListener {
            showToast(context, "Не получилось записать в БД")
            isNoError = false
        }
    return isNoError
}
fun signInUser(email: String, pass: String,
               onSuccess: () -> Unit, onFailure: () -> Unit) {
    Firebase.auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
        if (it.isSuccessful) {
            onSuccess()
        } else
            onFailure()
    }
}

suspend fun getUser(uid: String): User {
    try {
        val userRef = FirebaseDatabase.getInstance().getReference("Users").child(uid)
        userRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    user = snapshot.getValue(User::class.java)!!
                }
            }
            override fun onCancelled(error: DatabaseError) {
                Log.e("DB ERROR", "Failed to get data from DB: ${error.message}")
            }
        })
    } catch (e: Exception) {
        Log.e("ERROR", "Failed to get data from DB: ${e.message}")
    }
    return user
}

private fun showToast(context: Context, message: String) {
    Handler(Looper.getMainLooper()).post {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}