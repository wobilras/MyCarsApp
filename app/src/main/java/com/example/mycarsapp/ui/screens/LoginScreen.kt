package com.example.mycarsapp.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mycarsapp.data.signInUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {
    var textEmail by remember { mutableStateOf("") }
    var textPass by remember { mutableStateOf("") }
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Добро пожаловать!",
            modifier = Modifier
                .padding(top = 250.dp)
                .fillMaxWidth(),
        )
        TextField(
            value = textEmail,
            onValueChange = { newText ->
                textEmail = newText
            },
            singleLine = true,
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth()
                .padding(16.dp),
            textStyle = TextStyle(
                color = Color.White
            ),
            placeholder = {
                textEmail = "Email"
            }
        )
        TextField(
            value = textPass,
            onValueChange = { newText ->
                textPass = newText
            },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            textStyle = TextStyle(
                color = Color.White
            ),
            placeholder = {
                textPass = "Пароль"
            }
        )
        Button(onClick = {
            CoroutineScope(Dispatchers.IO).launch {
                signInUser(textEmail, textPass, {
                    CoroutineScope(Dispatchers.Main).launch {
                        Toast.makeText(context, "Успешный вход", Toast.LENGTH_SHORT).show()
                        navController.navigate("mapScreen")
                    }
                }, {
                    CoroutineScope(Dispatchers.Main).launch {
                        Toast.makeText(context, "Некорректные данные",
                            Toast.LENGTH_SHORT).show()
                    }
                })
            }
                         },
            modifier = Modifier
                .padding(top = 5.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)) {
            Text(
                text = "Войти",
                color = MaterialTheme.colorScheme.secondary
            )
        }
        Button(onClick = { navController.navigate("registrationScreen") },
            modifier = Modifier
                .padding(top = 5.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)) {
            Text(
                text = "Регистрация",
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}