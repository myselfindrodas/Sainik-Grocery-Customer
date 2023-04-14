package com.grocery.sainik_grocery.data.model.cartmodel.cartmodelresponse


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("cart")
    val cart: Cart
)