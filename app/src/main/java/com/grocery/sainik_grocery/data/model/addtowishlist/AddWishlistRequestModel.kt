package com.grocery.sainik_grocery.data.model.addtowishlist

import com.google.gson.annotations.SerializedName

class AddWishlistRequestModel(
    @SerializedName("urc_product_id")
    val product_id: String,
)