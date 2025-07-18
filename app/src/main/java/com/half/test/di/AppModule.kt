package com.half.test.di

import android.content.Context
import com.half.test.ClickRecorderApp
import com.half.test.data.local.dao.ClickRecordDao
import com.half.test.data.remote.RetrofitClient
import com.half.test.data.remote.api.ClickApiService
import com.half.test.data.remote.source.RemoteDataSource
import com.half.test.data.repository.ClickRepository
import com.half.test.ui.viewmodel.ClickViewModelFactory


object AppModule {
    private lateinit var application: ClickRecorderApp

    fun initialize(context: Context) {
        application = context.applicationContext as ClickRecorderApp
    }

    // 提供本地数据库访问
    fun provideClickRecordDao(): ClickRecordDao {
        return application.database.clickRecordDao()
    }

    // 提供远程API服务
    fun provideApiService(): ClickApiService {
        return RetrofitClient.instance
    }

    // 提供远程数据源
    fun provideRemoteDataSource(): RemoteDataSource {
        return RemoteDataSource(provideApiService())
    }

    // 提供仓库
    fun provideRepository(): ClickRepository {
        return ClickRepository(
            remoteDataSource = provideRemoteDataSource(),
            localDataSource = provideClickRecordDao()
        )
    }

    // 提供ViewModel工厂
    fun provideViewModelFactory(): ClickViewModelFactory {
        return ClickViewModelFactory(provideRepository())
    }


}