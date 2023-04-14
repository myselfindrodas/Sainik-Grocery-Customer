package com.grocery.sainik_grocery.data.model.add_order


import com.google.gson.annotations.SerializedName

data class AddOrderRequestModel(
    @SerializedName("address_id")
    val address_id: Int
)