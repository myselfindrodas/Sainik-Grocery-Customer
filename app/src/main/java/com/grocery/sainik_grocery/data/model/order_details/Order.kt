package com.grocery.sainik_grocery.data.model.order_details


import com.google.gson.annotations.SerializedName

data class Order(
    @SerializedName("address")
    val address: Address,
    @SerializedName("address_id")
    val addressId: Int, // 3
    @SerializedName("amount")
    val amount: Double, // 132
    @SerializedName("created_at")
    val createdAt: String, // 2023-04-11T06:02:09.000000Z
    @SerializedName("deleted_at")
    val deletedAt: String?, // null
    @SerializedName("delivery_charges")
    val deliveryCharges: Double, // 0
    @SerializedName("discount")
    val discount: Double, // 0
    @SerializedName("id")
    val id: Int, // 4
    @SerializedName("order_status")
    val order_status: Int, // 4
    @SerializedName("expt_delivery")
    val expt_delivery: String, // 4
    @SerializedName("order_history")
    val orderHistory: List<OrderHistory>,
    @SerializedName("order_reference_id")
    val orderReferenceId: String, // SAINIK780046
    @SerializedName("status_list")
    val status_list: List<StatusList>,
    @SerializedName("payment_id")
    val paymentId: String, // payment_1234
    @SerializedName("payment_method")
    val paymentMethod: Int, // 1
    @SerializedName("status")
    val status: Int, // 1
    @SerializedName("transaction_id")
    val transactionId: String, // transaction_12345
    @SerializedName("updated_at")
    val updatedAt: String, // 2023-04-11T06:02:53.000000Z
    @SerializedName("user_id")
    val userId: Int // 2
)