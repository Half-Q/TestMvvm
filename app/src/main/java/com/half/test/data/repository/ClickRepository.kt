package com.half.test.data.repository

import android.util.Log
import com.half.test.data.local.dao.ClickRecordDao
import com.half.test.data.local.entity.ClickRecordEntity
import com.half.test.data.model.ClickRecord
import com.half.test.data.remote.source.RemoteDataSource
import com.half.test.util.NetworkStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import java.util.Date


class ClickRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: ClickRecordDao
){
    val TAG = "ClickRepository"
    // 获取所有记录（本地数据库）
    fun getAllRecord(): Flow<List<ClickRecord>> {
        Log.d(TAG, "getAllRecord")
        return localDataSource.getAllRecord().map {
            entities -> entities.map { it.toClickRecord() }
        }
    }

    // 添加新纪录
    suspend fun addNewRecord(content : String) {
        Log.d(TAG, "addNewRecord:  ${content}")
        val timestamp = Date().toString()
        val record = ClickRecord(
            timestamp = timestamp,
            content = content
        )
        saveRecordLocally(record)
    }

    // 保存记录到本地
    private suspend fun saveRecordLocally(record: ClickRecord) {
        Log.d(TAG, "saveRecordLocally:  ${record}")
        val entity = ClickRecordEntity.fromClickRecord(record)
        localDataSource.insert(entity)
    }

    // 同步未同步的记录
    suspend fun syncRecords() : NetworkStatus<Unit> {
        Log.d(TAG, "syncRecords")
        val unSyncedRecords = localDataSource.getUnsyncedRecords()
            .map {it.toClickRecord()}

        if (unSyncedRecords.isEmpty()) {
            return NetworkStatus.Success(Unit)
        }

        return try {
            for (recode in unSyncedRecords) {
                when (val result = remoteDataSource.logClick(recode)) {
                    is NetworkStatus.Success -> {
                        // 更新本地记录
                        val updateRecord = recode.copy(
                            id = result.data.id,
                            isSynced = true
                        )
                        val entity = ClickRecordEntity.fromClickRecord(updateRecord)
                        localDataSource.update(entity)
                    }
                    is NetworkStatus.Error -> {
                        // 部分失败，继续尝试其他记录
                    }
                }
            }
            NetworkStatus.Success(Unit)
        }catch (e: Exception) {
            NetworkStatus.Error("Sync failed: ${e.message} ")
        }

    }

    // 刷新数据（远程 -> 本地）
    suspend fun refreshRecords() : NetworkStatus<Unit> {
        return when (val result = remoteDataSource.fetchHistory()) {
            is NetworkStatus.Success -> {
                localDataSource.deleteAll();
                result.data.forEach { record ->
                    val entity = ClickRecordEntity.fromClickRecord(record)
                    localDataSource.insert(entity)
                }
                NetworkStatus.Success(Unit)
            }
            is NetworkStatus.Error -> result
        }
    }


}