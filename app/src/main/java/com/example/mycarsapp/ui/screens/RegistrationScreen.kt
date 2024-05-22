package com.example.mycarsapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mycarsapp.R
import com.example.mycarsapp.data.signUpUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun RegistrationScreen(navController: NavController) {
    var textLogin by rememberSaveable { mutableStateOf("") }
    var textPass by rememberSaveable { mutableStateOf("") }
    var textPass1 by rememberSaveable { mutableStateOf("") }
    var textEmail by rememberSaveable { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = stringResource(id = R.string.registration),
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
                Text(text = stringResource(id = R.string.Login))
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
                Text(text = stringResource(id = R.string.email))
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
                Text(text = stringResource(id = R.string.Pass))
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
                Text(text = stringResource(id = R.string.repeatPass))
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
                text = stringResource(id = R.string.register),
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}