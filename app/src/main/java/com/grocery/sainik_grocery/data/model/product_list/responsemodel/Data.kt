package com.grocery.sainik_grocery.data.model.product_list.responsemodel


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("productList")
    val productList: ProductList,
    @SerializedName("totalProduct")
    val totalProduct: Int // 2
)