package com.example.mycarsapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mycarsapp.data.signUpUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(navController: NavController) {
    var textLogin by remember { mutableStateOf("") }
    var textPass by remember { mutableStateOf("") }
    var textPass1 by remember { mutableStateOf("") }
    var textEmail by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Регистрация",
            modifier = Modifier
                .padding(top = 150.dp)
                .fillMaxWidth()
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
                .padding(16.dp),
            placeholder = {
                Text(text = "Логин")
            }
        )
        TextField(
            value = textEmail,
            onValueChange = { newText ->
                textEmail = newText
            },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            placeholder = {
                Text(text = "Эл. почта")
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
            placeholder = {
                Text(text = "Пароль")
            }
        )
        TextField(
            value = textPass1,
            onValueChange = { newText ->
                textPass1 = newText
            },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            placeholder = {
                Text(text = "Повторите пароль")
            }
        )
        Button(
            onClick = {
                CoroutineScope(Dispatchers.IO).launch {
                    val signUpSuccess = signUpUser(textEmail, textPass, textPass1
                        , textLogin, context)
                    if (signUpSuccess){
                        CoroutineScope(Dispatchers.Main).launch {
                            navController.navigate("mapScreen")
                        }
                    }
                }
                navController.navigate("loginScreen")
                      },
            modifier = Modifier
                .padding(top = 5.dp)
                .fillMaxWidth(),
        ) {
            Text(
                text = "Зарегистрироваться",
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}