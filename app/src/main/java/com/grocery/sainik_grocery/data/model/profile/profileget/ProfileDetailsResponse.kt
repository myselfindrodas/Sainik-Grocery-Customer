package com.grocery.sainik_grocery.data.model.profile.profileget


import com.google.gson.annotations.SerializedName

data class ProfileDetailsResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("imageUrl")
    val imageUrl: String, // https://developer.shyamfuture.in/sainik-grocery/admin/uploads/userImages
    @SerializedName("message")
    val message: String, // Profile get successfully !!
    @SerializedName("status")
    val status: Boolean // true
)