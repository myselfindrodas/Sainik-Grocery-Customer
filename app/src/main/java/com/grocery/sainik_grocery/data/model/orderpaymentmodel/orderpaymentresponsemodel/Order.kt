package com.grocery.sainik_grocery.data.model.orderpaymentmodel.orderpaymentresponsemodel


import com.google.gson.annotations.SerializedName

data class Order(
    @SerializedName("address_id")
    val addressId: Int, // 3
    @SerializedName("amount")
    val amount: Double, // 132
    @SerializedName("created_at")
    val createdAt: String, // 2023-04-11T06:02:09.000000Z
    @SerializedName("deleted_at")
    val deletedAt: String?, // null
    @SerializedName("discount")
    val discount: Double, // 0
    @SerializedName("id")
    val id: Int, // 4
    @SerializedName("order_reference_id")
    val orderReferenceId: String, // SAINIK780046
    @SerializedName("order_status")
    val orderStatus: Int, // 0
    @SerializedName("payment_id")
    val paymentId: String?, // null
    @SerializedName("payment_method")
    val paymentMethod: Int, // 1
    @SerializedName("status")
    val status: Int, // 0
    @SerializedName("transaction_id")
    val transactionId: String?, // null
    @SerializedName("updated_at")
    val updatedAt: String, // 2023-04-11T06:02:09.000000Z
    @SerializedName("user_id")
    val userId: Int // 2
)