package com.grocery.sainik_grocery.data.model.urcmodel

import com.google.gson.annotations.SerializedName

data class UrcRequestModel(
    @SerializedName("latitude")
    val latitude: String,
    @SerializedName("longitude")
    val longitude: String
)