package com.grocery.sainik_grocery.data.model.notificationmodel.NotificationResponseModel


import com.google.gson.annotations.SerializedName

data class Notification(
    @SerializedName("current_page")
    val currentPage: Int, // 1
    @SerializedName("data")
    val `data`: List<DataNotification>,
    @SerializedName("first_page_url")
    val firstPageUrl: String, // https://developer.shyamfuture.in/sainik-grocery/admin/api/user/notification-list?page=1
    @SerializedName("from")
    val from: Int, // 1
    @SerializedName("last_page")
    val lastPage: Int, // 1
    @SerializedName("last_page_url")
    val lastPageUrl: String, // https://developer.shyamfuture.in/sainik-grocery/admin/api/user/notification-list?page=1
    @SerializedName("links")
    val links: List<Link>,
    @SerializedName("next_page_url")
    val nextPageUrl: Any?, // null
    @SerializedName("path")
    val path: String, // https://developer.shyamfuture.in/sainik-grocery/admin/api/user/notification-list
    @SerializedName("per_page")
    val perPage: Int, // 15
    @SerializedName("prev_page_url")
    val prevPageUrl: Any?, // null
    @SerializedName("to")
    val to: Int, // 2
    @SerializedName("total")
    val total: Int // 2
)