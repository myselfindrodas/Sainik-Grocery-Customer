package com.grocery.sainik_grocery.data.model.add_order.response


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("deliveryCharges")
    val deliveryCharges: Int, // 0
    @SerializedName("discount")
    val discount: Double, // 0
    @SerializedName("items")
    val items: Int, // 1
    @SerializedName("order")
    val order: Order,
    @SerializedName("price")
    val price: Double // 132
)