package com.shyamfuture.tantujayarnbank.data.model.paynow.response


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("order_id")
    val orderId: Int,
    @SerializedName("order_no")
    val orderNo: String,
    @SerializedName("notification_count")
val notificationCount: Int
)