package com.shyamfuture.tantujayarnbank.data.model.delete_cart

import com.google.gson.annotations.SerializedName

class DeleteCartItemRequestModel(
    @SerializedName("user_id")
    val user_id: Int,
    @SerializedName("cart_id")
    val cart_id: Int
) {
}