package com.grocery.sainik_grocery.data.model.addtowishlist.AddtowishlistResponseModel


import com.google.gson.annotations.SerializedName

data class Wishlist(
    @SerializedName("created_at")
    val createdAt: String, // 2023-04-07T05:47:38.000000Z
    @SerializedName("id")
    val id: Int, // 3
    @SerializedName("product_id")
    val productId: String, // 1
    @SerializedName("status")
    val status: Int, // 1
    @SerializedName("updated_at")
    val updatedAt: String, // 2023-04-07T05:47:38.000000Z
    @SerializedName("user_id")
    val userId: Int // 2
)