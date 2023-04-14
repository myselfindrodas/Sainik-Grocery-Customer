package com.grocery.sainik_grocery.data.model.saved_card.savecardmodelresponse


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("paymentCard")
    val paymentCard: List<PaymentCard>
)