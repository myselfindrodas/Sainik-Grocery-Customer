package com.shyamfuture.tantujayarnbank.data.model.societylist


import com.google.gson.annotations.SerializedName

data class SocietyListResponseModel(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("msg")
    val error: String,
    @SerializedName("status")
    val status: Int
)