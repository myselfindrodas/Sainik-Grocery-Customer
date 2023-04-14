package com.shyamfuture.tantujayarnbank.data.model.item_particular


import com.google.gson.annotations.SerializedName

data class ItemParticualarResponseModel(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("status")
    val status: Int
)