package com.grocery.sainik_grocery.data.model.dashboardmodel.dashboardresponse


import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("category")
    val category: CategoryX,
    @SerializedName("category_id")
    val categoryId: Int, // 2
    @SerializedName("id")
    val id: Int, // 1
    @SerializedName("product_id")
    val productId: Int // 1
)