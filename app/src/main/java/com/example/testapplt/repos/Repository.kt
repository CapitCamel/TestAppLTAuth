package com.example.testapplt.repos

import com.example.testapplt.data.ApiService
import com.example.testapplt.data.model.LoginRequest
import com.example.testapplt.data.model.LoginResponse
import com.example.testapplt.data.model.LogoutResponse
import com.example.testapplt.utils.CustomNetworkCall
import com.example.testapplt.utils.ResultWrapper

class Repository(private val api : ApiService) {
    suspend fun callApiLogin(email: String, pas: String) : ResultWrapper<LoginResponse> {
        return CustomNetworkCall.safeApiCall() {
            api.login(LoginRequest(email, pas))
        }
    }
    suspend fun callApiLogout(token: String) : ResultWrapper<LogoutResponse> {
        return CustomNetworkCall.safeApiCall() {
            api.logout(token)
        }
    }
}