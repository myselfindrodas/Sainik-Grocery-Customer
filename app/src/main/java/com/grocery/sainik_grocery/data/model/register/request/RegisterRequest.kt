package com.shyamfuture.tantujayarnbank.data.model.register.request

import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody

data class RegisterRequest(
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("adhar_document")
    val adharDocument: MultipartBody.Part,
    @SerializedName("society_id")
    val societyId: String,
    @SerializedName("icard")
    val icard: MultipartBody.Part,
    @SerializedName("phone_number")
    val phoneNumber: String,
    @SerializedName("language")
    val language: Int
)