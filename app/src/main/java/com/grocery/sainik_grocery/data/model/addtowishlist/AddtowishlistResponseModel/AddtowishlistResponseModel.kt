package com.grocery.sainik_grocery.data.model.addtowishlist.AddtowishlistResponseModel


import com.google.gson.annotations.SerializedName

data class AddtowishlistResponseModel(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String, // Product added to wishlist successfully.
    @SerializedName("status")
    val status: Boolean // true
)