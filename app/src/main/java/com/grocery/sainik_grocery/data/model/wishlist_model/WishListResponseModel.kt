package com.grocery.sainik_grocery.data.model.wishlist_model


import com.google.gson.annotations.SerializedName

data class WishListResponseModel(
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