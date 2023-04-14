package com.grocery.sainik_grocery.data.model.product_details


import com.google.gson.annotations.SerializedName

data class ProductImage(
    @SerializedName("id")
    val id: Int, // 1
    @SerializedName("image")
    val image: String, // wqe
    @SerializedName("product_id")
    val productId: Int, // 2
    var isSelected: Boolean = false
)