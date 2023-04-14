package com.grocery.sainik_grocery.data.model.cartlist_model


import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("id")
    val id: Int, // 2
    @SerializedName("image")
    val image: String?, // null
    @SerializedName("name")
    val name: String, // shop
    @SerializedName("units_of_measurement_types")
    val unitsOfMeasurementTypes: String // Kg
)