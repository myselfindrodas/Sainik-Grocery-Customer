package com.grocery.sainik_grocery.data.model.product_list.responsemodel


import com.google.gson.annotations.SerializedName

data class DataProductList(
    @SerializedName("category")
    val category: Category,
    @SerializedName("category_id")
    val categoryId: Int, // 2
    @SerializedName("id")
    val id: Int, // 1
    @SerializedName("is_daily_essential")
    val isDailyEssential: Int, // 1
    @SerializedName("is_featured")
    val isFeatured: Int, // 1
    @SerializedName("product")
    val product: Product,
    @SerializedName("product_id")
    val productId: Int, // 1
    @SerializedName("product_name")
    val productName: String, // abc
    @SerializedName("selling_price")
    val sellingPrice: Double, // 60
    @SerializedName("discount")
    val discount: Double, // 60
    @SerializedName("total_selling")
    val totalSelling: Int? // 66
)