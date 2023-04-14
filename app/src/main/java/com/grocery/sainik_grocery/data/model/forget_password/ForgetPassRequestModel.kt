package com.shyamfuture.tantujayarnbank.data.model.forget_password


import com.google.gson.annotations.SerializedName

data class ForgetPassRequestModel(
    @SerializedName("email")
    val email: String
)