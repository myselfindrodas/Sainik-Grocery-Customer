package com.grocery.sainik_grocery.data.model.my_order_list


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("order")
    val order: List<Order>
)