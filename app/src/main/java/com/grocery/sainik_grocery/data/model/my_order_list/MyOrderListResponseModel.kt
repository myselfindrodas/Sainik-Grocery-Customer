package com.grocery.sainik_grocery.data.model.my_order_list


import com.google.gson.annotations.SerializedName

data class MyOrderListResponseModel(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String, // Data get successfully
    @SerializedName("orderImageUrl")
    val orderImageUrl: String, // https://developer.shyamfuture.in/sainik-grocery/admin/uploads/orderImages/order.png
    @SerializedName("status")
    val status: Boolean // true
)