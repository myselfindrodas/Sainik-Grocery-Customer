package com.shyamfuture.tantujayarnbank.data.model.item_particular


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("bundle_size")
    val bundleSize: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)