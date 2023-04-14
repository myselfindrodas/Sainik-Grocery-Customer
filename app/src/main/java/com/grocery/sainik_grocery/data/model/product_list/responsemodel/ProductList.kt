package com.grocery.sainik_grocery.data.model.product_list.responsemodel


import com.google.gson.annotations.SerializedName

data class ProductList(
    @SerializedName("current_page")
    val currentPage: Int, // 1
    @SerializedName("data")
    val `data`: List<DataProductList>,
    @SerializedName("first_page_url")
    val firstPageUrl: String, // https://developer.shyamfuture.in/sainik-grocery/admin/api/user/urc-product-list?page=1
    @SerializedName("from")
    val from: Int, // 1
    @SerializedName("last_page")
    val lastPage: Int, // 1
    @SerializedName("last_page_url")
    val lastPageUrl: String, // https://developer.shyamfuture.in/sainik-grocery/admin/api/user/urc-product-list?page=1
    @SerializedName("links")
    val links: List<Link>,
    @SerializedName("next_page_url")
    val nextPageUrl: String?, // null
    @SerializedName("path")
    val path: String, // https://developer.shyamfuture.in/sainik-grocery/admin/api/user/urc-product-list
    @SerializedName("per_page")
    val perPage: Int, // 10
    @SerializedName("prev_page_url")
    val prevPageUrl: String?, // null
    @SerializedName("to")
    val to: Int, // 2
    @SerializedName("total")
    val total: Int // 2
)