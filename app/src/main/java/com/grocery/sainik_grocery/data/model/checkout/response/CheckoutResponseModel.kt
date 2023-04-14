package com.shyamfuture.tantujayarnbank.data.model.checkout.response


import com.google.gson.annotations.SerializedName

data class CheckoutResponseModel(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("status")
    val status: Int
)