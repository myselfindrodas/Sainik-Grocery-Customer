package com.shyamfuture.tantujayarnbank.data.model.societylist


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)