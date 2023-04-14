package com.grocery.sainik_grocery.data.model.urcmodel.urcresponse


import com.google.gson.annotations.SerializedName

data class UrcModel(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String, // Data get successfully
    @SerializedName("status")
    val status: Boolean // true
)