package com.grocery.sainik_grocery.data.model.wishlist_model


import com.google.gson.annotations.SerializedName

data class Link(
    @SerializedName("active")
    val active: Boolean, // false
    @SerializedName("label")
    val label: String, // &laquo; Previous
    @SerializedName("url")
    val url: String? // https://developer.shyamfuture.in/sainik-grocery/admin/api/user/wishlist-list?page=1
)