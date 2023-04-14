package com.shyamfuture.tantujayarnbank.data.model.hub_list


import com.google.gson.annotations.SerializedName

data class HubListResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("msg")
    val msg: String,
    @SerializedName("status")
    val status: Int
)