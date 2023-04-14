package com.grocery.sainik_grocery.data.model.saved_card.addprimaryaddressresponse


import com.google.gson.annotations.SerializedName

data class AddPaymentPrimaryCardResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String, // This card is set as your primary card.
    @SerializedName("status")
    val status: Boolean // true
)