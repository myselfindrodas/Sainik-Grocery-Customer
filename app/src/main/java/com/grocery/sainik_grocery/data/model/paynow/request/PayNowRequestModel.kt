package com.shyamfuture.tantujayarnbank.data.model.paynow.request


import com.google.gson.annotations.SerializedName

data class PayNowRequestModel(
    @SerializedName("cart_id")
    val cartId: List<CartId>,
    @SerializedName("cgst")
    val cgst: Double,
    @SerializedName("gst")
    val gst: Double,
    @SerializedName("payment_mode")
    var paymentMode: Int,
    @SerializedName("sgst")
    val sgst: Double,
    @SerializedName("sub_total")
    val subTotal: Double,
    @SerializedName("total")
    val total: Double,
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("pickup_date")
    var pickupDate: String,
    @SerializedName("payment_status")
    var payment_status: Int=1,
    @SerializedName("transaction_id")
    var transaction_id: String=""
)