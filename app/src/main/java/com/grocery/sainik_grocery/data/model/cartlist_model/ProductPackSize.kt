package com.grocery.sainik_grocery.data.model.cartlist_model


import com.google.gson.annotations.SerializedName

data class ProductPackSize(
    @SerializedName("id")
    val id: Int, // 1
    @SerializedName("size")
    val size: String, // 500gm
)