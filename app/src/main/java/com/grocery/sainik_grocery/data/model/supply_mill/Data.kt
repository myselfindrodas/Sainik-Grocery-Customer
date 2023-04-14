package com.shyamfuture.tantujayarnbank.data.model.supply_mill


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("quantity")
    val quantity: String,
    @SerializedName("selling_cost")
    val sellingCost: String
)