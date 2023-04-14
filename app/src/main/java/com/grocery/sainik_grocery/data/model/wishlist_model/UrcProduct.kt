package com.grocery.sainik_grocery.data.model.wishlist_model


import com.google.gson.annotations.SerializedName

data class UrcProduct(
    @SerializedName("category_id")
    val categoryId: Int, // 1
    @SerializedName("discount")
    val discount: Double, // 12.5
    @SerializedName("id")
    val id: Int, // 6
    @SerializedName("is_daily_essential")
    val isDailyEssential: Int, // 1
    @SerializedName("is_featured")
    val isFeatured: Int, // 1
    @SerializedName("product")
    val product: Product,
    @SerializedName("product_id")
    val productId: Int, // 2
    @SerializedName("product_name")
    val productName: Any?, // null
    @SerializedName("selling_price")
    val sellingPrice: Int, // 77
    @SerializedName("selling_quantity")
    val sellingQuantity: Int // 0
)