package ru.dadetail.data.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import ru.dadetail.data.entity.Login

interface AuthApi {
    @POST("api/v2/customer/login/")
    suspend fun login(@Body login: Login): Response<Unit>
}