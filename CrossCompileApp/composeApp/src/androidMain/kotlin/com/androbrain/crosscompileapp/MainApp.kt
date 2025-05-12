package com.androbrain.crosscompileapp

import android.app.Application
import com.androbrain.crosscompileapp.di.initKoin
import org.koin.android.ext.koin.androidContext

class MainApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin { androidContext(this@MainApp) }
    }
}
