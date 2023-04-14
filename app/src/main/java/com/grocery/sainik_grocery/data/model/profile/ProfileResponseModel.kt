package com.shyamfuture.tantujayarnbank.data.model.profile


import com.google.gson.annotations.SerializedName

data class ProfileResponseModel(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("status")
    val status: Int
)