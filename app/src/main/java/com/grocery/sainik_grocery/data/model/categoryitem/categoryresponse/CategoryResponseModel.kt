package com.grocery.sainik_grocery.data.model.categoryitem.categoryresponse


import com.google.gson.annotations.SerializedName

data class CategoryResponseModel(
    @SerializedName("categoryImageUrl")
    val categoryImageUrl: String, // https://developer.shyamfuture.in/sainik-grocery/admin/uploads/categoryImages
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String, // Data get successfully
    @SerializedName("status")
    val status: Boolean // true
)