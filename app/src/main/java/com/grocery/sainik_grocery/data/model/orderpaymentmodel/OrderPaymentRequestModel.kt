package com.grocery.sainik_grocery.data.model.orderpaymentmodel

import com.google.gson.annotations.SerializedName

class OrderPaymentRequestModel(
    @SerializedName("order_id")
    val order_id: String,
    @SerializedName("payment_id")
    val payment_id: String,
    @SerializedName("transaction_id")
    val transaction_id: String
)