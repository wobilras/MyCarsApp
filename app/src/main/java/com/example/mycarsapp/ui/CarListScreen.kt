package com.example.mycarsapp.ui

import android.content.res.Resources.Theme
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import com.example.mycarsapp.R
import com.example.mycarsapp.data.Car
import com.example.mycarsapp.data.carList


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun CarListScreen(onCarSelected: (Car) -> Unit) {
    var searchText by rememberSaveable { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    Column {
        TextField(
            value = searchText,
            onValueChange = { searchText = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            placeholder = { Text("Search...") },
            singleLine = true,
            trailingIcon = {
                if (searchText.isNotBlank()) {
                    Icon(
                        Icons.Default.Clear,
                        contentDescription = "clear text",
                        modifier = Modifier
                            .clickable {
                                searchText = ""
                                keyboardController?.hide()
                            }
                    )
                }
            }
        )
        //Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(carList) { car ->
                CarListItem(car = car) { onCarSelected(car) }
            }
        }
    }
}

@Composable
fun CarListItem(car: Car, onCarSelected: (Car) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCarSelected(car) }
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = car.name,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            style = LocalTextStyle.current
        )
        Box(
            modifier = Modifier
                .size(100.dp)
                .padding(end = 16.dp)
        ) {
            androidx.compose.foundation.Image(
                painter = painterResource(id = car.imageResId),
                contentDescription = car.name
            )
        }
        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "${car.hourlyRate} $/hour",
                    fontSize = 16.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = car.distance,
                    fontSize = 16.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewCarListScreen() {
    CarListScreen {}
}