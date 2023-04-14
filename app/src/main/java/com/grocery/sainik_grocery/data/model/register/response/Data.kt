package com.shyamfuture.tantujayarnbank.data.model.register.response


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("otp")
    val otp: Int,
    @SerializedName("phone_number")
    val phoneNumber: String
)