package com.example.mymoviedb

import AppComponent
import DaggerAppComponent
import android.app.Application

class MainApp : Application() {
    val appComponent: AppComponent = DaggerAppComponent.create()
}