package com.half.test.data.remote.api

import com.half.test.data.remote.dto.ClickRecordDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ClickApiService {
    @POST
    suspend fun logClick(@Body record: ClickRecordDto): Response<ClickRecordDto>

    @GET
    suspend fun getClickHistory(): Response<List<ClickRecordDto>>

}