package com.grocery.sainik_grocery.data.model.address.address_list


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("address")
    val address: List<Addres>
)