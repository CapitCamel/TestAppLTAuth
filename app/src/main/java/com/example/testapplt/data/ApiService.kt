package com.example.testapplt.data

import com.example.testapplt.data.model.LoginRequest
import com.example.testapplt.data.model.LoginResponse
import com.example.testapplt.data.model.LogoutResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {

    @POST("auth/login")
    suspend fun login(
        @Body body: LoginRequest
    ): LoginResponse

    @POST("auth/logout")
    suspend fun logout(
        @Header("authToken") token: String
    ): LogoutResponse
}