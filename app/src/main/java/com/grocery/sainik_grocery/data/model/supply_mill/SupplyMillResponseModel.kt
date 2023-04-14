package com.shyamfuture.tantujayarnbank.data.model.supply_mill


import com.google.gson.annotations.SerializedName

data class SupplyMillResponseModel(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("status")
    val status: Int
)