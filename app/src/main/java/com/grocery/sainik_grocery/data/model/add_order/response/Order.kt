package com.grocery.sainik_grocery.data.model.add_order.response


import com.google.gson.annotations.SerializedName

data class Order(
    @SerializedName("address_id")
    val addressId: String, // 3
    @SerializedName("amount")
    val amount: Double, // 132
    @SerializedName("created_at")
    val createdAt: String, // 2023-04-10T11:36:20.000000Z
    @SerializedName("discount")
    val discount: Double, // 0
    @SerializedName("id")
    val id: Int, // 2
    @SerializedName("order_reference_id")
    val orderReferenceId: String, // SAINIK423048
    @SerializedName("order_status")
    val orderStatus: Int, // 0
    @SerializedName("payment_method")
    val paymentMethod: Int, // 1
    @SerializedName("status")
    val status: Int, // 0
    @SerializedName("updated_at")
    val updatedAt: String, // 2023-04-10T11:36:20.000000Z
    @SerializedName("user_id")
    val userId: Int // 2
)