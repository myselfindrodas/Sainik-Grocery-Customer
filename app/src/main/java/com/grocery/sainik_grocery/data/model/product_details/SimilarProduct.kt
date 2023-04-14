package com.grocery.sainik_grocery.data.model.product_details


import com.google.gson.annotations.SerializedName

data class SimilarProduct(
    @SerializedName("category")
    val category: Category,
    @SerializedName("category_id")
    val categoryId: Int, // 1
    @SerializedName("discount")
    val discount: Double, // 3
    @SerializedName("id")
    val id: Int, // 2
    @SerializedName("is_daily_essential")
    val isDailyEssential: Int, // 1
    @SerializedName("is_featured")
    val isFeatured: Int, // 1
    @SerializedName("product")
    val product: ProductSimilar,
    @SerializedName("product_id")
    val productId: Int, // 2
    @SerializedName("product_name")
    val productName: String?, // null
    @SerializedName("selling_price")
    val sellingPrice: Double, // 77
    @SerializedName("selling_quantity")
    val sellingQuantity: Int // 0
)