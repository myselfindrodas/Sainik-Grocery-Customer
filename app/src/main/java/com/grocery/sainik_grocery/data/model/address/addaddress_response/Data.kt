package com.grocery.sainik_grocery.data.model.address.addaddress_response


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("address")
    val address: Address
)