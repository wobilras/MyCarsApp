package com.example.mycarsapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mycarsapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {
    var textLogin by remember { mutableStateOf("") }
    var textPass by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.auto_reglog),
                contentScale = ContentScale.FillBounds
            )
            .padding(16.dp)
    ) {
        Text(text = "Добро пожаловать!",
            modifier = Modifier
                .padding(top = 250.dp)
                .fillMaxWidth(),
            color = Color.Black
        )
        TextField(
            value = textLogin,
            onValueChange = { newText ->
                textLogin = newText
            },
            singleLine = true,
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth()
                //.background(color = Color(0xFF000000), shape = MaterialTheme.shapes.small)
                .padding(16.dp),
            textStyle = TextStyle(
                color = Color.White
            ),
            placeholder = {
                textLogin = "Логин"
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
                //.background(color = Color(0xFF000000), shape = MaterialTheme.shapes.small)
                .padding(16.dp),
            textStyle = TextStyle(
                color = Color.White
            ),
            placeholder = {
                textPass = "Пароль"
            }
        )
        Button(onClick = { navController.navigate("mapScreen") },
            modifier = Modifier
                .padding(top = 5.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color.Black)) {
            Text(
                text = "Войти",
                color = Color.White
            )
        }
        Button(onClick = { navController.navigate("registrationScreen") },
            modifier = Modifier
                .padding(top = 5.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color.Black)) {
            Text(
                text = "Регистрация",
                color = Color.White
            )
        }
    }
}