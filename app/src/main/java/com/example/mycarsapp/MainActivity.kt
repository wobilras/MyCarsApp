package com.example.mycarsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.mycarsapp.data.getThemeState
import com.example.mycarsapp.ui.MainContainer
import com.example.mycarsapp.ui.screens.LocalFinish
import com.example.mycarsapp.ui.theme.MyCarsAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            fun restartActivity(){
                finish()
                startActivity(intent)
            }
            val darkTheme = getThemeState(LocalContext.current)
            MyCarsAppTheme(darkTheme = darkTheme, dynamicColor = false) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CompositionLocalProvider(LocalFinish provides {
                        restartActivity()
                    }) {
                        MainContainer()
                    }
                }
            }
        }
    }
}
