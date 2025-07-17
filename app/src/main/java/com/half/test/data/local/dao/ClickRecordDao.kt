package com.half.test.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.half.test.data.local.entity.ClickRecordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ClickRecordDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(record: ClickRecordEntity): Long

    @Update
    suspend fun update(record: ClickRecordEntity)

    @Query("SELECT * FROM click_records ORDER BY timestamp DESC")
    fun getAllRecord() : Flow<List<ClickRecordEntity>>

    @Query("SELECT * FROM click_records WHERE isSynced = 0")
    suspend fun getUnsyncedRecords(): List<ClickRecordEntity>

    @Query("DELETE FROM click_records")
    suspend fun deleteAll()

}