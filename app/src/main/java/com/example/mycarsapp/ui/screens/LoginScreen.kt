package com.example.mycarsapp.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.example.mycarsapp.data.signInUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(navController: NavController) {
    var textEmail by rememberSaveable { mutableStateOf("") }
    var textPass by rememberSaveable { mutableStateOf("") }
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
            //.paint(painterResource(id =R.drawable.auto_reglog))

    ) {
        Text(
            text = stringResource(id = R.string.welcome),
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
        Button(onClick = {
            CoroutineScope(Dispatchers.IO).launch {
                signInUser(textEmail, textPass, {
                    CoroutineScope(Dispatchers.Main).launch {
                        Toast.makeText(context, R.string.successful_enter, Toast.LENGTH_SHORT).show()
                        navController.navigate("mapScreen")
                    }
                }, {
                    CoroutineScope(Dispatchers.Main).launch {
                        Toast.makeText(context, R.string.wrong_data,
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
                text = stringResource(id = R.string.enter),
                color = MaterialTheme.colorScheme.secondary
            )
        }
        Button(onClick = { navController.navigate("registrationScreen") },
            modifier = Modifier
                .padding(top = 5.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)) {
            Text(
                text = stringResource(id = R.string.registration),
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}