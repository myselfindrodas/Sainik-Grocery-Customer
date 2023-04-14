package com.grocery.sainik_grocery.data.model.login


import com.google.gson.annotations.SerializedName

data class LoginResponseModel(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val msg: String,
    @SerializedName("status")
    val status: Boolean
)