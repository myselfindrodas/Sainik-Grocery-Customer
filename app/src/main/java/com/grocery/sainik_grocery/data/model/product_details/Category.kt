package com.grocery.sainik_grocery.data.model.product_details


import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("id")
    val id: Int, // 1
    @SerializedName("image")
    val image: Any?, // null
    @SerializedName("name")
    val name: String // sancks
)