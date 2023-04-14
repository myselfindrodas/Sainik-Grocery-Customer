package com.grocery.sainik_grocery.data.model.product_list.responsemodel


import com.google.gson.annotations.SerializedName

data class Link(
    @SerializedName("active")
    val active: Boolean, // false
    @SerializedName("label")
    val label: String, // &laquo; Previous
    @SerializedName("url")
    val url: String? // https://developer.shyamfuture.in/sainik-grocery/admin/api/user/urc-product-list?page=1
)