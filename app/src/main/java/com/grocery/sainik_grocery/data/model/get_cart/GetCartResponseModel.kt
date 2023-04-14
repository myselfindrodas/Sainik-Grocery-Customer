package com.shyamfuture.tantujayarnbank.data.model.get_cart


import com.google.gson.annotations.SerializedName

data class GetCartResponseModel(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("status")
    val status: Int,
    @SerializedName("subtotal")
    val subtotal: Double,
    @SerializedName("gst")
    val gst: Double,
    @SerializedName("cgst")
    val cgst: Double,
    @SerializedName("sgst")
    val sgst: Double,
    @SerializedName("total")
    val total: Double
)