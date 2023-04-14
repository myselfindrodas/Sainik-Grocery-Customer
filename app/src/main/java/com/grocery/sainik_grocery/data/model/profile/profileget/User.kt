package com.grocery.sainik_grocery.data.model.profile.profileget


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("address")
    val address: Any?, // null
    @SerializedName("birthday")
    val birthday: String, // null
    @SerializedName("created_at")
    val createdAt: Any?, // null
    @SerializedName("deleted_at")
    val deletedAt: Any?, // null
    @SerializedName("device_type")
    val deviceType: Any?, // null
    @SerializedName("email")
    val email: String, // user@gmail.com
    @SerializedName("email_validate")
    val emailValidate: Int, // 0
    @SerializedName("email_verified_at")
    val emailVerifiedAt: Any?, // null
    @SerializedName("gender")
    val gender: Int, // 1
    @SerializedName("id")
    val id: Int, // 2
    @SerializedName("image")
    val image: String,
    @SerializedName("last_name")
    val lastName: String?, // null
    @SerializedName("latitude")
    val latitude: String, // 28.418716
    @SerializedName("longitude")
    val longitude: String, // 77.0478998
    @SerializedName("name")
    val name: String, // user
    @SerializedName("otp")
    val otp: Any?, // null
    @SerializedName("phone")
    val phone: String, // 7031552022
    @SerializedName("phone_validate")
    val phoneValidate: Int, // 0
    @SerializedName("quota")
    val quota: Any?, // null
    @SerializedName("rank")
    val rank: Any?, // null
    @SerializedName("remember_token")
    val rememberToken: Any?, // null
    @SerializedName("role_id")
    val roleId: Int, // 2
    @SerializedName("status")
    val status: Int, // 1
    @SerializedName("updated_at")
    val updatedAt: Any?, // null
    @SerializedName("zip_codes")
    val zipCodes: Any? // null
)