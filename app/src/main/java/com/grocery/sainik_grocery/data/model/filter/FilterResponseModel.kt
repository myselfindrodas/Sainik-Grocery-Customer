package com.grocery.sainik_grocery.data.model.filter


import com.google.gson.annotations.SerializedName

data class FilterResponseModel(
    @SerializedName("categoryImageUrl")
    val categoryImageUrl: String, // https://developer.shyamfuture.in/sainik-grocery/admin/uploads/categoryImages
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String, // Data get successfully
    @SerializedName("productImageUrl")
    val productImageUrl: String, // https://developer.shyamfuture.in/sainik-grocery/admin/uploads/productImages
    @SerializedName("status")
    val status: Boolean // true
)