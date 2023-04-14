package com.grocery.sainik_grocery.data.model.login


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("token")
    val token: String,
    @field:SerializedName("user")
    val user: user? = null,
)

data class user(
    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("role_id")
    val role_id: Int? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("phone")
    val phone: String? = null,

    @field:SerializedName("otp")
    val otp: String? = null,

    @field:SerializedName("zip_codes")
    val zip_codes: String? = null,

    @field:SerializedName("image")
    val image: String? = null,

    @field:SerializedName("address")
    val address: String? = null,

    @field:SerializedName("last_name")
    val last_name: String? = null,

    @field:SerializedName("birthday")
    val birthday: String? = null,

    @field:SerializedName("gender")
    val gender: String? = null,

    @field:SerializedName("rank")
    val rank: String? = null,


    @field:SerializedName("quota")
    val quota: String? = null,

    @field:SerializedName("latitude")
    val latitude: String? = null,

    @field:SerializedName("longitude")
    val longitude: String? = null,

    @field:SerializedName("device_type")
    val device_type: String? = null,

    @field:SerializedName("phone_validate")
    val phone_validate: Int? = null,

    @field:SerializedName("email_validate")
    val email_validate: Int? = null,

    @field:SerializedName("status")
    val status: Int? = null,


    @field:SerializedName("email_verified_at")
    val email_verified_at: String? = null,

    @field:SerializedName("remember_token")
    val remember_token: String? = null,

    @field:SerializedName("created_at")
    val created_at: String? = null,

    @field:SerializedName("updated_at")
    val updated_at: String? = null,

    @field:SerializedName("deleted_at")
    val deleted_at: String? = null
)