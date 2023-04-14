package com.shyamfuture.tantujayarnbank.data.model.checkout.request


import com.google.gson.annotations.SerializedName

data class CartId(
    @SerializedName("cat_id")
    val catId: String,
    @SerializedName("hub_id")
    val hubId: String,
    @SerializedName("max_quantity")
    val maxQuantity: String,
    @SerializedName("mill_id")
    val millId: String,
    @SerializedName("quantity")
    val quantity: String,
    @SerializedName("yarn_id")
    val yarnId: String
)