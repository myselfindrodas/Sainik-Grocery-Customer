package com.grocery.sainik_grocery.data.model.notificationmodel.NotificationResponseModel


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("notification")
    val notification: Notification,
    @SerializedName("totalData")
    val totalData: Int // 2
)