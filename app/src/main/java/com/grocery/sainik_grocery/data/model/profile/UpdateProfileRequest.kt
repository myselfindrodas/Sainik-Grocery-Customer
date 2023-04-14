package com.shyamfuture.tantujayarnbank.data.model.profile

import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody

data class UpdateProfileRequest(
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("adhar_document")
    val adharDocument: MultipartBody.Part,
    @SerializedName("society_id")
    val societyId: String,
    @SerializedName("icard")
    val icard: MultipartBody.Part,
    @SerializedName("profile_pic")
    val profilePic: MultipartBody.Part,
    @SerializedName("phone_number")
    val phoneNumber: String,
    @SerializedName("user_id")
    val userId: String
)