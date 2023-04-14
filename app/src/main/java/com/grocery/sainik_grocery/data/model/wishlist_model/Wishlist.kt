package com.grocery.sainik_grocery.data.model.wishlist_model


import com.google.gson.annotations.SerializedName

data class Wishlist(
    @SerializedName("current_page")
    val currentPage: Int, // 1
    @SerializedName("data")
    val `data`: List<DataWishListItem>,
    @SerializedName("first_page_url")
    val firstPageUrl: String, // https://developer.shyamfuture.in/sainik-grocery/admin/api/user/wishlist-list?page=1
    @SerializedName("from")
    val from: Int, // 1
    @SerializedName("last_page")
    val lastPage: Int, // 1
    @SerializedName("last_page_url")
    val lastPageUrl: String, // https://developer.shyamfuture.in/sainik-grocery/admin/api/user/wishlist-list?page=1
    @SerializedName("links")
    val links: List<Link>,
    @SerializedName("next_page_url")
    val nextPageUrl: Any?, // null
    @SerializedName("path")
    val path: String, // https://developer.shyamfuture.in/sainik-grocery/admin/api/user/wishlist-list
    @SerializedName("per_page")
    val perPage: Int, // 15
    @SerializedName("prev_page_url")
    val prevPageUrl: Any?, // null
    @SerializedName("to")
    val to: Int, // 1
    @SerializedName("total")
    val total: Int // 1
)