package com.grocery.sainik_grocery.data.model.address.address_list


import com.google.gson.annotations.SerializedName

data class AddressListResponseModel(
    @SerializedName("categoryImageUrl")
    val categoryImageUrl: String, // https://developer.shyamfuture.in/sainik-grocery/admin/uploads/categoryImages
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String, // Data get successfully
    @SerializedName("productImageUrl")
    val productImageUrl: String, // https://developer.shyamfuture.in/sainik-grocery/admin/uploads/productImages
    @SerializedName("status")
    val status: Boolean // true
)