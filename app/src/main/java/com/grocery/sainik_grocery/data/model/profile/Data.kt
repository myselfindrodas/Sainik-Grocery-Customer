package com.shyamfuture.tantujayarnbank.data.model.profile


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("adhar_document")
    val adharDocument: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("icard")
    val icard: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("phone_number")
    val phoneNumber: String,
    @SerializedName("profile_pic")
    val profilePic: String,
    @SerializedName("society_id")
    val societyId: String,
    @SerializedName("placed_order")
    val placed_order: Int,
    @SerializedName("pending_order")
    val pending_order: Int,
    @SerializedName("success_order")
    val success_order: Int,
    @SerializedName("cotton_limit")
    val cotton_limit: Double,
    @SerializedName("silk_limit")
    val silk_limit: Double,
    @SerializedName("notification_count")
    val notificationCount: Int,
    @SerializedName("count")
    val count: Int

)