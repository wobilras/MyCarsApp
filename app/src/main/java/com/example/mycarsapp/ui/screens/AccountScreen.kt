package com.example.mycarsapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mycarsapp.R
import com.example.mycarsapp.data.User
import com.google.firebase.auth.FirebaseAuth

@Composable
fun AccountScreen(
    user: User,
    navController: NavController,
    onEditProfileClick: () -> Unit
) {
    val finishActivity = LocalFinish.current
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        Image(
            painter = painterResource(id = user.userPhotoResId!!),
            contentDescription = null,
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )

        Text(text = user.userName!!, style = MaterialTheme.typography.bodyLarge)

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = stringResource(id = R.string.rate)+" ${user.userRating}/5")
            Text(text = stringResource(id = R.string.trips_count)+" ${user.tripsCompleted}")
            if (user.fine!! > 0)
                Text(text = stringResource(id = R.string.you_have_fine)+" ${user.fine} \n" +
                        stringResource(id = R.string.pay_as_possible))
            else Text(text = stringResource(id = R.string.dont_have_fine))
        }

        Button(onClick = { /* Handle edit profile click */ },
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)) {
            Text(text = stringResource(id = R.string.edit),
                color = MaterialTheme.colorScheme.secondary)
        }
        Button(onClick = { /* TODO */ },
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)) {
            Text(text = stringResource(id = R.string.support),
                color = MaterialTheme.colorScheme.secondary)
        }
        Button(onClick = { navController.navigate("settingsScreen") }) {
            Text(text = stringResource(id = R.string.settings),
                color = MaterialTheme.colorScheme.secondary)
        }
        Button(onClick = { FirebaseAuth.getInstance().signOut()
            finishActivity()
        }) {
            Text(text = stringResource(id = R.string.sign_out),
                color = MaterialTheme.colorScheme.secondary)
        }
    }
}