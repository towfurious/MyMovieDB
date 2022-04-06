package com.example.mymoviedb

import AppComponent
import DaggerAppComponent
import android.app.Application

class MainApp : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}