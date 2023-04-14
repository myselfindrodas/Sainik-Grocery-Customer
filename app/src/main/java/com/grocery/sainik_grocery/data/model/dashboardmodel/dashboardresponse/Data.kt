package com.grocery.sainik_grocery.data.model.dashboardmodel.dashboardresponse


import com.google.gson.annotations.SerializedName
import com.grocery.sainik_grocery.data.model.product_list.responsemodel.DataProductList

data class Data(
    @SerializedName("category")
    val category: List<Category>,
    @SerializedName("essentialProducts")
    val essentialProducts: List<DataProductList>,
    @SerializedName("featuredProducts")
    val featuredProducts: List<DataProductList>,
    @SerializedName("topSellingProducts")
    val topSellingProducts: List<DataProductList>
)