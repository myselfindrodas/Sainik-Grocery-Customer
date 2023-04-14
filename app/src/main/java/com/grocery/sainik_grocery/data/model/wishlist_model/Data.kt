package com.grocery.sainik_grocery.data.model.wishlist_model


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("totalData")
    val totalData: Int, // 1
    @SerializedName("Wishlist")
    val wishlist: Wishlist
)