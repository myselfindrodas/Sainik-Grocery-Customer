package com.grocery.sainik_grocery.data.model.order_details


import com.google.gson.annotations.SerializedName

data class ProductPackSize(
    @SerializedName("discount")
    val discount: Double, // 0
    @SerializedName("id")
    val id: Int, // 2
    @SerializedName("selling_price")
    val sellingPrice: Double, // 44
    @SerializedName("size")
    val size: String // 100gm
)