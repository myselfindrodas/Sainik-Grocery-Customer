package com.grocery.sainik_grocery.data.model.order_details


import com.google.gson.annotations.SerializedName

data class StatusList(
    @SerializedName("created_at")
    val createdAt: String, // 2023-04-11T06:02:53.000000Z
    @SerializedName("deleted_at")
    val deletedAt: String?, // null
    @SerializedName("id")
    val id: Int, // 1
    @SerializedName("order_id")
    val orderId: Int, // 4
    @SerializedName("order_status")
    val orderStatus: Int, // 1
    @SerializedName("status")
    val status: Int, // 1
    @SerializedName("updated_at")
    val updatedAt: String // 2023-04-11T06:02:53.000000Z
)