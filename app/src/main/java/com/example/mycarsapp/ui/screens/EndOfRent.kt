package com.example.mycarsapp.ui.screens

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mycarsapp.R

@Composable
fun EndOfRent( navController: NavController) {
    val photoNumRight = 0
    val photoNumLeft = 0
    val photoNumTop = 0
    val photoNumBottom = 0

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(text = "Добавьте фото авто с каждой стороны", fontWeight = FontWeight.Bold)

        Box(
            modifier = Modifier
                //.fillMaxSize()
                //.size(400.dp,650.dp)
                .padding(16.dp)
        ) {
            Button(
                onClick = { /* Действие при нажатии */ },
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
                onClick = { /* Действие при нажатии */ },
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
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Car Image",
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxSize(),
                contentScale = ContentScale.Fit
            )
            Button(
                onClick = { /* Действие при нажатии */ },
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
            Button(
                onClick = { /* Действие при нажатии */ },
                modifier = Modifier
                    .align(Alignment.BottomCenter),
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
        }
        Button(onClick = { navController.navigate("mapScreen") },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.secondary
            )) {
            Text(text = "Завершить")
        }
    }
}

@Preview
@Composable
fun PreviewEnd(){
    //EndOfRent()
}