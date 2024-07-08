package com.jovelcasais.kotlinlibraryutilisation.ui.app

import android.app.Application
import com.jovelcasais.kotlinlibraryutilisation.utilities.helpers.GraphHelper
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        GraphHelper.provide(this)
    }
}