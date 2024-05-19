package com.example.mycarsapp.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.mycarsapp.data.getThemeState
import com.example.mycarsapp.data.setThemeState

val LocalFinish = compositionLocalOf<() -> Unit> {
    error("Finish function not provided")
}
@Composable
fun SettingsScreen() {
    val context = LocalContext.current
    val finishActivity = LocalFinish.current
    var isDarkTheme = getThemeState(context)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Switch(checked = isDarkTheme, onCheckedChange = {
            isDarkTheme = it
            setThemeState(context,it)
            Toast.makeText(context,"Для смены темы приложение будет перезапущено", Toast.LENGTH_SHORT).show()
            finishActivity()
        })
        Text(text = "https://yandex.ru/legal/maps_termsofuse - " +
                "«Условия использования сервиса Яндекс.Карты")
    }

}