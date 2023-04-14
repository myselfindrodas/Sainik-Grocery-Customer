package com.grocery.sainik_grocery.data.model.addcard.AddcardResponseModel


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("paymentCard")
    val paymentCard: PaymentCard
)