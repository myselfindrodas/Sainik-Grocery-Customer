package com.shyamfuture.tantujayarnbank.data.model.get_cart


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("amount")
    val amount: String,
    @SerializedName("bundle")
    val bundle: String,
    @SerializedName("cat_id")
    val catId: String,
    @SerializedName("category_name")
    val categoryName: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("hub_id")
    val hubId: String,
    @SerializedName("hubs_name")
    val hubsName: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("mill_id")
    val millId: String,
    @SerializedName("mills_name")
    val millsName: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("max_quantity")
    val max_quantity: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("user_id")
    val userId: String,
    @SerializedName("weight")
    val weight: String,
    @SerializedName("yarn_id")
    val yarnId: String,
    @SerializedName("yarns_name")
    val yarnsName: String
)