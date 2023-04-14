package com.grocery.sainik_grocery.data.model.order_summery


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("deliveryCharges")
    val deliveryCharges: Double, // 0
    @SerializedName("discount")
    val discount: Double, // 0
    @SerializedName("items")
    val items: Int, // 1
    @SerializedName("price")
    val price: Double // 44
)