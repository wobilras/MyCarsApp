package com.example.mycarsapp.ui.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mycarsapp.R
import com.example.mycarsapp.data.photosMap
import com.example.mycarsapp.ui.theme.small_padding

@Composable
fun EndOfRent( navController: NavController, carId: String) {
    var photoNumRight by remember { mutableIntStateOf(0) }
    var photoNumLeft by remember { mutableIntStateOf(0) }
    var photoNumTop by remember { mutableIntStateOf(0) }
    var photoNumBottom by remember { mutableIntStateOf(0) }
    var currentSide by remember { mutableStateOf("") }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            photosMap.getOrPut(carId) { mutableListOf() }.add(uri)
            when (currentSide) {
                "Left" -> photoNumLeft++
                "Right" -> photoNumRight++
                "Top" -> photoNumTop++
                "Bottom" -> photoNumBottom++
            }
        }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(small_padding)
    ) {
        Text(text = stringResource(id = R.string.addAuto), fontWeight = FontWeight.Bold)

        Box {
            Button(
                onClick = {
                    currentSide = "Left"
                    launcher.launch("image/*") },
                modifier = Modifier
                    .align(Alignment.CenterStart),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.secondary
                )
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "$photoNumLeft")
                    Icon(Icons.Filled.AddCircle, contentDescription = "Left")
                }
            }
            Button(
                onClick = {
                    currentSide = "Right"
                    launcher.launch("image/*") },
                modifier = Modifier
                    .align(Alignment.CenterEnd),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.secondary
                )
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "$photoNumRight")
                    Icon(Icons.Filled.AddCircle, contentDescription = "Right")
                }
            }
            Image(
                painter = painterResource(id = R.drawable.auto_front_1),
                contentDescription = "Car Image",
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxSize()
                    .padding(top = 60.dp, bottom = 110.dp),
                contentScale = ContentScale.Fit
            )
            Button(
                onClick = {
                    currentSide = "Top"
                    launcher.launch("image/*") },
                modifier = Modifier
                    .align(Alignment.TopCenter),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.secondary
                )
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "$photoNumTop")
                    Icon(Icons.Filled.AddCircle, contentDescription = "Top")
                }
            }
            Box(modifier = Modifier.align(Alignment.BottomCenter)) {
                Column {
                    Button(
                        onClick = {
                            currentSide = "Bottom"
                            launcher.launch("image/*")
                        },
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.secondary
                        )
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "$photoNumBottom")
                            Icon(Icons.Filled.AddCircle, contentDescription = "Bottom")
                        }
                    }
                    Button(
                        onClick = { navController.navigate("mapScreen") },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.secondary
                        )
                    ) {
                        Text(text = stringResource(id = R.string.end))
                    }
                }
            }
        }
    }
}