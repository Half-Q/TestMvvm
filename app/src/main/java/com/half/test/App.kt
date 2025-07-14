package com.half.test

import android.app.Application
import com.half.test.data.local.App
import com.half.test.data.local.AppDatabase

class ClickRecorderApp : Application() {
    val database: AppDatabase by lazy { AppDatabase.getInstance(this) }

    override fun onCreate() {
        super.onCreate()
        AppModule.initialize(this)
    }
}