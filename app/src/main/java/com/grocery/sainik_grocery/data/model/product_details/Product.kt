package com.grocery.sainik_grocery.data.model.product_details


import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("about")
    val about: String, // In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document or a typeface without relying on meaningful content. Lorem ipsum may be used as a placeholder before final copy is available.
    @SerializedName("benifits")
    val benifits: String, // In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document or a typeface without relying on meaningful content. Lorem ipsum may be used as a placeholder before final copy is available.
    @SerializedName("id")
    val id: Int, // 2
    @SerializedName("image")
    val image: Any?, // null
    @SerializedName("name")
    val name: String, // shop
    @SerializedName("price")
    val price: Double, // 55
    @SerializedName("product_images")
    val productImages: List<ProductImage>,
    @SerializedName("storage_and_uses")
    val storageAndUses: String, // In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document or a typeface without relying on meaningful content. Lorem ipsum may be used as a placeholder before final copy is available.
    @SerializedName("title")
    val title: String, // shop
    @SerializedName("units_of_measurement_types")
    val unitsOfMeasurementTypes: String // Kg
)