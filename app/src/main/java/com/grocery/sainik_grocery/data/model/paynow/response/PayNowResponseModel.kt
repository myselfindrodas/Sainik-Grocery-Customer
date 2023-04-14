package com.shyamfuture.tantujayarnbank.data.model.paynow.response


import com.google.gson.annotations.SerializedName

data class PayNowResponseModel(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("status")
    val status: Int
)