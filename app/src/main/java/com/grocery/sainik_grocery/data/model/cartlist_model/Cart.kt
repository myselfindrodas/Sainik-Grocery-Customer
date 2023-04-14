package com.grocery.sainik_grocery.data.model.cartlist_model


import com.google.gson.annotations.SerializedName

data class Cart(
    @SerializedName("created_at")
    val createdAt: String, // 2023-04-05T12:14:58.000000Z
    @SerializedName("deleted_at")
    val deletedAt: Any?, // null
    @SerializedName("id")
    val id: Int, // 1
    @SerializedName("is_order")
    val isOrder: Int, // 0
    @SerializedName("order_id")
    val orderId: String?, // null
    @SerializedName("price")
    val price: Double, // 80
    @SerializedName("product")
    val product: Product,
    @SerializedName("product_pack_size")
    val product_pack_size: ProductPackSize,
    @SerializedName("product_id")
    val productId: Int, // 2
    @SerializedName("product_pack_size_id")
    val productPackSizeId: Int, // 1
    @SerializedName("quantity")
    val quantity: Int, // 2
    @SerializedName("status")
    val status: Int, // 1
    @SerializedName("updated_at")
    val updatedAt: String, // 2023-04-05T12:14:58.000000Z
    @SerializedName("user_id")
    val userId: Int // 2
)