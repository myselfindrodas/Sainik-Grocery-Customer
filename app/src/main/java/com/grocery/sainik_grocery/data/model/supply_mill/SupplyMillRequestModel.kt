package com.shyamfuture.tantujayarnbank.data.model.supply_mill


import com.google.gson.annotations.SerializedName

data class SupplyMillRequestModel(
    @SerializedName("cat_id")
    val catId: Int,
    @SerializedName("hub_id")
    val hubId: Int,
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("yarn_id")
    val yarnId: Int
)