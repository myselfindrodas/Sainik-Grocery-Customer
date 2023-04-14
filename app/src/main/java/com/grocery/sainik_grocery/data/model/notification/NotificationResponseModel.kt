package com.shyamfuture.tantujayarnbank.data.model.notification


import com.google.gson.annotations.SerializedName

data class NotificationResponseModel(
    @SerializedName("data")
    val `data`: List<NotificationData>,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("status")
    val status: Int
)