package com.grocery.sainik_grocery.data.model.logout


import com.google.gson.annotations.SerializedName

data class LogoutResponseModel(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String, // User logout successfully
    @SerializedName("status")
    val status: Boolean // true
)