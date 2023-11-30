package com.example.paymentsapp.network

import com.example.paymentsapp.data.APIKEY
import com.example.paymentsapp.data.V
import com.example.paymentsapp.network.BodyRegistration
import com.example.paymentsapp.network.ModelPaymentsResponse
import com.example.paymentsapp.network.ModelTokenResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST


interface PaymentsApi {
    @POST("login")
    suspend fun getToken(
        @Header("app-key") apKey: String = APIKEY,
        @Header("v") v: String = V,
        @Body bodyRegistration: BodyRegistration,
    ): ModelTokenResponse

    @GET("payments")
    suspend fun getPayments(
        @Header("app-key") apKey: String = APIKEY,
        @Header("v") v: String = V,
        @Header("token") token: String
    ): ModelPaymentsResponse
}

