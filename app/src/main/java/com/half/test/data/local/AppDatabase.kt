package com.half.test.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.half.test.data.local.dao.ClickRecordDao
import com.half.test.data.model.ClickRecord

@Database(entities = [ClickRecord::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun clickRecordDao() : ClickRecordDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "click_recorder_db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}