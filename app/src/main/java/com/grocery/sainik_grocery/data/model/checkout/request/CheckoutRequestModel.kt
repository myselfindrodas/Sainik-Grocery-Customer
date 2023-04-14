package com.shyamfuture.tantujayarnbank.data.model.checkout.request


import com.google.gson.annotations.SerializedName

data class CheckoutRequestModel(
    @SerializedName("cart_id")
    val cartId: List<CartId>,
    @SerializedName("user_id")
    val userId: Int
)