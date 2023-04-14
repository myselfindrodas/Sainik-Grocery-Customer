package com.grocery.sainik_grocery.data.model.dashboardmodel.dashboardresponse


import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("id")
    val id: Int, // 1
    @SerializedName("image")
    val image: Any?, // null
    @SerializedName("name")
    val name: String, // salt
    @SerializedName("price")
    val price: Double, // 25
    @SerializedName("title")
    val title: String, // salt
    @SerializedName("units_of_measurement_types")
    val units_of_measurement_types: String // Kg
)