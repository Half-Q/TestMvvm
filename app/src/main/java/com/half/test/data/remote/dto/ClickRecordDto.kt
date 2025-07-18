package com.half.test.data.remote.dto

import com.half.test.data.model.ClickRecord
import com.squareup.moshi.Json

data class ClickRecordDto (
    @field:Json(name = "id") val id: Int? = null,
    @field:Json(name = "timestamp") val timestamp: String,
    @field:Json(name = "content") val content: String
){
    //转换为领域模型
    fun toClickRecord(): ClickRecord {
        return ClickRecord (
            id = id,
            timestamp = timestamp,
            content = content,
            isSynced = true
        )
    }

    // 从领域模型创建DTO
    companion object {
        fun fromClickRecord(record: ClickRecord): ClickRecordDto {
            return ClickRecordDto(
                id = record.id,
                timestamp = record.timestamp,
                content = record.content
            )
        }
    }
}