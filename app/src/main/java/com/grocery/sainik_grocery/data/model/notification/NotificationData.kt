package com.shyamfuture.tantujayarnbank.data.model.notification


import com.google.gson.annotations.SerializedName

data class NotificationData(
    @SerializedName("category_name")
    val categoryName: String,
    @SerializedName("cgst")
    val cgst: String,
    @SerializedName("message")
    val message: String,
    @SerializedName("code")
    val code: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("gst")
    val gst: String,
    @SerializedName("hubs_name")
    val hubsName: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("mills_name")
    val millsName: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("order_details_id")
    val orderDetailsId: String,
    @SerializedName("order_no")
    val orderNo: Any,
    @SerializedName("payment_mode")
    val paymentMode: String,
    @SerializedName("payment_status")
    val paymentStatus: String,
    @SerializedName("pickup_date")
    val pickupDate: Any,
    @SerializedName("sgst")
    val sgst: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("sub_total")
    val subTotal: String,
    @SerializedName("total")
    val total: String,
    @SerializedName("transaction_id")
    val transactionId: Any,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("user_id")
    val userId: String,
    @SerializedName("yarns_name")
    val yarnsName: String
)