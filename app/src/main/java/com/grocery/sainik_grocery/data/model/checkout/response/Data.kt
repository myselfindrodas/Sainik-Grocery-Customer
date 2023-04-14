package com.shyamfuture.tantujayarnbank.data.model.checkout.response


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("card_payment")
    val cardPayment: Int,
    @SerializedName("cod")
    val cod: Int
)