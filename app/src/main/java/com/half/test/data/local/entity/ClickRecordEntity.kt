package com.half.test.data.local.entity

import android.util.Log
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.half.test.data.model.ClickRecord

@Entity(tableName = "click_records")
data class ClickRecordEntity (
    @PrimaryKey(autoGenerate = true) val localId: Int = 0,
    val remoteId: Int? = null,
    val timestamp: String,
    val content: String,
    val isSynced: Boolean = false
) {

    fun toClickRecord(): ClickRecord {
        Log.d("ClickRecordEntity", "toClickRecord")
        return ClickRecord(
            id = remoteId,
            localId = localId,
            timestamp = timestamp,
            content = content,
            isSynced = isSynced
        )
    }

    companion object {
        fun fromClickRecord(record: ClickRecord): ClickRecordEntity {
            Log.d("ClickRecordEntity", "fromClickRecord")
            return ClickRecordEntity(
                localId = record.localId,
                remoteId = record.id,
                timestamp = record.timestamp,
                content = record.content,
                isSynced = record.isSynced
            )
        }
    }
}