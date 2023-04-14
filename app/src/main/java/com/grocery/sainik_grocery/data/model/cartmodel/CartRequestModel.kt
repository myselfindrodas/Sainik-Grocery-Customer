package com.grocery.sainik_grocery.data.model.cartmodel

import com.google.gson.annotations.SerializedName

class CartRequestModel(
    @SerializedName("product_pack_size_id")
    val product_pack_size_id: String,
    @SerializedName("is_added")
    val is_added: String

)