package com.grocery.sainik_grocery.data.model.address.addaddress_response


import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("apartment_name")
    val apartmentName: String, // sd
    @SerializedName("area")
    val area: String, // asewq
    @SerializedName("city")
    val city: String, // wdw
    @SerializedName("created_at")
    val createdAt: String, // 2023-04-06T11:16:59.000000Z
    @SerializedName("house_no")
    val houseNo: String, // 34
    @SerializedName("id")
    val id: Int, // 1
    @SerializedName("is_primary")
    val isPrimary: Int, // 1
    @SerializedName("pincode")
    val pincode: String, // 232123
    @SerializedName("status")
    val status: Int, // 1
    @SerializedName("street_details")
    val streetDetails: String, // sdas
    @SerializedName("type")
    val type: String, // Others
    @SerializedName("updated_at")
    val updatedAt: String, // 2023-04-06T11:16:59.000000Z
    @SerializedName("user_id")
    val userId: Int // 2
)