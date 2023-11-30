package com.example.paymentsapp.network
import com.google.gson.annotations.SerializedName

data class ModelTokenResponse(
    @SerializedName("response")
    var response: ResponseToken?
)

data class ResponseToken(
    @SerializedName("token")
    val token: String
)

data class BodyRegistration(val login: String, val password: String)

data class ModelPaymentsResponse(
    @SerializedName("response")
    var payments: List<Payment>
)

data class Payment(
    @SerializedName("id")
    var id: String,
    @SerializedName("title")
    var title: String,
    @SerializedName("amount")
    var amount: String,
    @SerializedName("created")
    var created: String
)