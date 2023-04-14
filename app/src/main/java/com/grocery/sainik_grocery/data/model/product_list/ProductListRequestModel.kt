package com.grocery.sainik_grocery.data.model.product_list

import com.google.gson.annotations.SerializedName

data class ProductListRequestModel(
    @SerializedName("urc_id")
    val urc_id: String,
    @SerializedName("is_featured")
    var is_featured: Int=0,
    @SerializedName("is_essential")
    var is_essential: Int=0,
    @SerializedName("category_id")
    var category_id: Int=0,
    @SerializedName("is_low_price")
    var is_low_price: Int=0,
    @SerializedName("is_high_price")
    var is_high_price: Int=0,
    @SerializedName("top_selling")
    var top_selling: Int=0,
    @SerializedName("alphabetical")
    var alphabetical: Int=0,
    @SerializedName("keywords")
    var keywords: String="",
    @SerializedName("attribute_id")
    var attribute_id: List<Int> = arrayListOf(),
    @SerializedName("price")
    var price: String = ""
)