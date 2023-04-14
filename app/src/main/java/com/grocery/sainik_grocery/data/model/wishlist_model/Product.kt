package com.grocery.sainik_grocery.data.model.wishlist_model


import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("id")
    val id: Int, // 2
    @SerializedName("image")
    val image: Any?, // null
    @SerializedName("name")
    val name: String, // shop
    @SerializedName("price")
    val price: Int, // 88
    @SerializedName("title")
    val title: String, // shop
    @SerializedName("units_of_measurement_types")
    val unitsOfMeasurementTypes: String // Kg
)