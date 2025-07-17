package com.half.test.data.remote.source

import com.half.test.data.model.ClickRecord
import com.half.test.data.remote.api.ClickApiService
import com.half.test.data.remote.dto.ClickRecordDto
import com.half.test.util.NetworkStatus

class RemoteDataSource(private val apiService: ClickApiService) {
    suspend fun logClick(record: ClickRecord): NetworkStatus<ClickRecord> {
        return try {
            val dto = ClickRecordDto.fromClickRecord(record)
            val response = apiService.logClick(dto)

            if (response.isSuccessful && response.body() != null) {
                val savedRecord = response.body()!!.toClickRecord()
                NetworkStatus.Success(savedRecord)
            } else {
                NetworkStatus.Error("Server error: ${response.code()}")
            }
        } catch (e: Exception) {
            NetworkStatus.Error(e.message ?: "Network error")
        }
    }

}