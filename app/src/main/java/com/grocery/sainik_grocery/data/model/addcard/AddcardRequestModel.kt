package com.grocery.sainik_grocery.data.model.addcard

import com.google.gson.annotations.SerializedName

class AddcardRequestModel(
    @SerializedName("holder_name")
    val holder_name: String,
    @SerializedName("card_number")
    val card_number: String,
    @SerializedName("exp_date")
    val exp_date: String,
    @SerializedName("cvv")
    val cvv: String,
)