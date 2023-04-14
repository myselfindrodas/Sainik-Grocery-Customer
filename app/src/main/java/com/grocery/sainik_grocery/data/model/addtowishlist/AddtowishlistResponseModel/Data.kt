package com.grocery.sainik_grocery.data.model.addtowishlist.AddtowishlistResponseModel


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("wishlist")
    val wishlist: Wishlist
)