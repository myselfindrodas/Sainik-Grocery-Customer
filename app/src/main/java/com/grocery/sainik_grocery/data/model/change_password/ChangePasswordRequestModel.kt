package com.shyamfuture.tantujayarnbank.data.model.change_password

import com.google.gson.annotations.SerializedName

class ChangePasswordRequestModel(
    @SerializedName("user_id")
    val user_id: Int,
    @SerializedName("old_password")
    val oldPassword: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("conf_pass")
    val conf_pass: String
) {
}