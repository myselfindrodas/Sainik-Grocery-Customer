package com.grocery.sainik_grocery.data.model.profile.profileupdate


import com.google.gson.annotations.SerializedName

data class ProfileUpdateResponseModel(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("imageUrl")
    val imageUrl: String, // https://developer.shyamfuture.in/sainik-grocery/admin/uploads/userImages
    @SerializedName("message")
    val message: String, // Profile update successfully !!
    @SerializedName("status")
    val status: Boolean // true
)