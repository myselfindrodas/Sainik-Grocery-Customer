package com.grocery.sainik_grocery.data.model.cartmodel.cartmodelresponse


import com.google.gson.annotations.SerializedName

data class Cart(
    @SerializedName("created_at")
    val createdAt: String, // 2023-04-05T12:14:58.000000Z
    @SerializedName("id")
    val id: Int, // 1
    @SerializedName("price")
    val price: Double, // 80
    @SerializedName("product_id")
    val productId: String, // 2
    @SerializedName("product_pack_size_id")
    val productPackSizeId: String, // 1
    @SerializedName("quantity")
    val quantity: String, // 2
    @SerializedName("status")
    val status: Int, // 1
    @SerializedName("updated_at")
    val updatedAt: String, // 2023-04-05T12:14:58.000000Z
    @SerializedName("user_id")
    val userId: Int // 2
)