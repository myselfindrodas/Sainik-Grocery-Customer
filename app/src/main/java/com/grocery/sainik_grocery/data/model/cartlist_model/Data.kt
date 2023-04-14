package com.grocery.sainik_grocery.data.model.cartlist_model


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("cart")
    val cart: List<Cart>
)