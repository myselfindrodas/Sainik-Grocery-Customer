package com.shyamfuture.tantujayarnbank.data.model.paymentstatus


import com.google.gson.annotations.SerializedName

data class PaymentStatusRequestModel(
    @SerializedName("order_id")
    val orderId: String?,
    @SerializedName("payment_status")
    val paymentStatus: Int?,
    @SerializedName("transaction_id")
    val transactionId: String?,
    @SerializedName("user_id")
    val userId: Int?
)