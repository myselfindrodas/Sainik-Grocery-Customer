package com.grocery.sainik_grocery.data.model.address

import com.google.gson.annotations.SerializedName

class AddAddressRequestModel(
    @SerializedName("address_id")
    val address_id: Int=0,
    @SerializedName("house_no")
    val house_no: String,
    @SerializedName("apartment_name")
    val apartment_name: String,
    @SerializedName("street_details")
    val street_details: String,
    @SerializedName("area")
    val area: String,
    @SerializedName("city")
    val city: String,
    @SerializedName("pincode")
    val pincode: String,
    @SerializedName("type")
    val type: String

)