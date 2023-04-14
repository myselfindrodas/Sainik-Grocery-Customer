package com.grocery.sainik_grocery.data.model.address.addaddress_response


import com.google.gson.annotations.SerializedName

data class AddAddressResponseModel(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String, // Address added successfully.
    @SerializedName("status")
    val status: Boolean // true
)