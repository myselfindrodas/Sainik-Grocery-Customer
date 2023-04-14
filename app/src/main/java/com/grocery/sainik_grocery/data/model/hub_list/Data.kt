package com.shyamfuture.tantujayarnbank.data.model.hub_list


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)