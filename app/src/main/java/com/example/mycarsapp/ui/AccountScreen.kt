package com.example.mycarsapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mycarsapp.R

@Composable
fun AccountScreen(
    userName: String,
    userPhotoResId: Int,
    userRating: Int,
    tripsCompleted: Int,
    fine: Int,
    onEditProfileClick: () -> Unit
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        Image(
            painter = painterResource(id = userPhotoResId),
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Text(text = userName, style = MaterialTheme.typography.bodyLarge)

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = "Рейтинг: $userRating/5")
            Text(text = "Кол-во совершенных поездок: $tripsCompleted")
            if (fine > 0)
                Text(text = "У вас имеются штрафы в кол-ве $fine \n" +
                        "Погасите их как можно скорее")
            else Text(text = "У вас отсутствуют штрафы!")
        }

        Button(onClick = { /* Handle edit profile click */ },
            colors = ButtonDefaults.buttonColors(Color.Black)) {
            Text(text = "Редактировать данные",
                color = Color.White)
        }
        Button(onClick = { /* TODO */ },
            colors = ButtonDefaults.buttonColors(Color.Black)) {
            Text(text = "Поддержка",
                color = Color.White)
        }
    }
}

@Preview
@Composable
fun AccountScreenPreview() {
    AccountScreen(userName = "Wob",
        userPhotoResId = R.drawable.ic_launcher_foreground,
        userRating = 5,
        tripsCompleted = 7,
        fine = 0) {}
}