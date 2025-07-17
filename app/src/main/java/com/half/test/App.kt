package com.half.test

import android.app.Application
import com.half.test.data.local.AppDatabase
import com.half.test.di.AppModule

class ClickRecorderApp : Application() {
    val database: AppDatabase by lazy { AppDatabase.getInstance(this) }

    override fun onCreate() {
        super.onCreate()
        AppModule.initialize(this)
    }
}