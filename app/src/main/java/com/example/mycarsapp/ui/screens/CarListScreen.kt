package com.example.mycarsapp.ui.screens

import android.content.Context
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mycarsapp.R
import com.example.mycarsapp.data.Car
import com.example.mycarsapp.data.SearchStatus
import com.example.mycarsapp.data.addToSearchHistory
import com.example.mycarsapp.data.carList
import com.example.mycarsapp.data.clearSearchHistory
import com.example.mycarsapp.data.firebaseGetCar
import com.example.mycarsapp.data.getSearchHistory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun CarListScreen(onCarSelected: (Car) -> Unit) {
    var searchText by rememberSaveable { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    var searchStatus by remember { mutableStateOf(SearchStatus.LOADING) }
    val context = LocalContext.current
    var showSearchHistory by remember { mutableStateOf(false) }

    Column {
        TextField(
            value = searchText,
            onValueChange = { searchText = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .onFocusChanged { focusState ->
                    showSearchHistory = focusState.isFocused
                },
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
            },
            keyboardActions = KeyboardActions(onAny = {
                addToSearchHistory(context, searchText)
            })
        )
        key(searchText) {
            firebaseGetCar(searchText) { cars, status ->
                carList = cars
                searchStatus = status
            }

            if (showSearchHistory) {
                SearchHistory(
                    context,
                    onItemClick = { selectedItem ->
                        searchText = selectedItem
                    },
                    onClearHistoryClick = {
                        clearSearchHistory(context)
                    }
                )
            }
            when (searchStatus) {
                SearchStatus.LOADING -> {
                    CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                }

                SearchStatus.SUCCESS -> {
                    LazyColumn {
                        items(carList) { car ->
                            CarListItem(car = car) { onCarSelected(car) }
                        }
                    }
                }

                SearchStatus.ERROR -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Error occurred", modifier = Modifier.padding(16.dp))
                        Button(
                            onClick = {
                                searchStatus = SearchStatus.LOADING
                                CoroutineScope(Dispatchers.IO).launch {
                                    firebaseGetCar(searchText) { cars, status ->
                                        carList = cars
                                        searchStatus = status
                                    }
                                }
                            },
                            modifier = Modifier.padding(8.dp),
                            colors = ButtonDefaults.buttonColors(Color.Black)

                        ) {
                            Text("Refresh")
                        }
                    }
                }

                SearchStatus.ERROR_NOT_FOUND -> {
                    Text("Ничего не найдено", modifier = Modifier.padding(16.dp))
                }
            }
        }
    }
}

@Composable
fun SearchHistory(
    context: Context,
    onItemClick: (String) -> Unit,
    onClearHistoryClick: () -> Unit
) {
    val history = remember { getSearchHistory(context) }

    LazyColumn {
        items(history) { item ->
            Text(
                text = item,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable { onItemClick(item) }
            )
        }
    }
    Button(
        onClick = { onClearHistoryClick() },
        modifier = Modifier
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(Color.Black)
    ) {
        Text(
            "Очистить историю",
            style = LocalTextStyle.current
        )
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
            text = car.name!!,
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
                painter = painterResource(id = car.imageResId!!),
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
                    text = car.distance!!,
                    fontSize = 16.sp,
                    color = Color.Gray
                )
            }
        }
    }
}
