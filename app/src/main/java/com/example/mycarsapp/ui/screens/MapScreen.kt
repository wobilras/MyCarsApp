package com.example.mycarsapp.ui.screens

import android.view.LayoutInflater
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.mycarsapp.R
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.mapview.MapView

private lateinit var mapView: MapView
@Composable
fun MapScreen(navController: NavController) {

    MapKitFactory.initialize(LocalContext.current)
    AndroidView(
        factory = { context ->
            val view = LayoutInflater.from(context).inflate(R.layout.yandex_map, null, false)

            mapView = view.findViewById<MapView>(R.id.mapview)

            view // return the view
        },
        update = { view ->
            MapKitFactory.getInstance().onStart()
            mapView.onStart()
        }
    )
}