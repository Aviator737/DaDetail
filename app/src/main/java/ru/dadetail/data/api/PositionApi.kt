package ru.dadetail.data.api

import retrofit2.Response
import retrofit2.http.GET
import ru.dadetail.data.entity.PositionsResponse

interface PositionApi {

    @GET("/api/v2/client/positions/")
    suspend fun getPositions(): Response<PositionsResponse>
}