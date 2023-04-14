package com.grocery.sainik_grocery.data.model.addcard.AddcardResponseModel


import com.google.gson.annotations.SerializedName

data class AddcardResponseModel(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String, // Card added to cardlist successfully.
    @SerializedName("status")
    val status: Boolean // true
)