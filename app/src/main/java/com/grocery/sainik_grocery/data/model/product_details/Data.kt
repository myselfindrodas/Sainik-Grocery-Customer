package com.grocery.sainik_grocery.data.model.product_details


import com.google.gson.annotations.SerializedName
import com.grocery.sainik_grocery.data.model.product_list.responsemodel.DataProductList

data class Data(
    @SerializedName("productDetails")
    val productDetails: ProductDetails,
    @SerializedName("similarProducts")
    val similarProducts: List<DataProductList>
)