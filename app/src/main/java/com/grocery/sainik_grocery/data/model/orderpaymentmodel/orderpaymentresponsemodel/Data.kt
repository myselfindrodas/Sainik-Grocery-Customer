package com.grocery.sainik_grocery.data.model.orderpaymentmodel.orderpaymentresponsemodel


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("order")
    val order: Order
)