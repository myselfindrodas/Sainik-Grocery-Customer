package com.shyamfuture.tantujayarnbank.data.model.forget_password


import com.google.gson.annotations.SerializedName

data class ForgetPasswordResponseModel(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val msg: String,
    @SerializedName("status")
    val status: Boolean
)