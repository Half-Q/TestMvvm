package com.half.test.di

import android.content.Context
import com.half.test.ClickRecorderApp
import com.half.test.data.local.dao.ClickRecordDao


object AppModule {
    private lateinit var application: ClickRecorderApp

    fun initialize(context: Context) {
        application = context.applicationContext as ClickRecorderApp
    }

    //本地数据库
    fun provideClickRecordDao(): ClickRecordDao {
        return application.database.clickRecordDao()
    }


}