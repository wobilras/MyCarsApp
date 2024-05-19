package com.example.mycarsapp

import android.app.Application
import com.yandex.mapkit.MapKitFactory

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        val mapkitApiKey = BuildConfig.MAPKIT_API_KEY
        MapKitFactory.setApiKey(mapkitApiKey)
    }
}