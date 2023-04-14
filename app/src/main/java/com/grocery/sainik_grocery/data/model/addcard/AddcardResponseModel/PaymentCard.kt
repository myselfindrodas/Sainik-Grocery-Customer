package com.grocery.sainik_grocery.data.model.addcard.AddcardResponseModel


import com.google.gson.annotations.SerializedName

data class PaymentCard(
    @SerializedName("card_number")
    val cardNumber: String, // 2342213277
    @SerializedName("created_at")
    val createdAt: String, // 2023-04-07T10:16:52.000000Z
    @SerializedName("cvv")
    val cvv: String, // 23w
    @SerializedName("exp_date")
    val expDate: String, // 12-12-2023
    @SerializedName("holder_name")
    val holderName: String, // abc
    @SerializedName("id")
    val id: Int, // 3
    @SerializedName("is_primary")
    val isPrimary: Int, // 0
    @SerializedName("status")
    val status: Int, // 1
    @SerializedName("updated_at")
    val updatedAt: String, // 2023-04-07T10:16:52.000000Z
    @SerializedName("user_id")
    val userId: Int // 2
)