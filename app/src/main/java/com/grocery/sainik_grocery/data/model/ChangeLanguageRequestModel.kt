package com.shyamfuture.tantujayarnbank.data.model

import com.google.gson.annotations.SerializedName

class ChangeLanguageRequestModel(
    @SerializedName("user_id")
    val user_id: Int,
    @SerializedName("language")
    val language: String
) {
}