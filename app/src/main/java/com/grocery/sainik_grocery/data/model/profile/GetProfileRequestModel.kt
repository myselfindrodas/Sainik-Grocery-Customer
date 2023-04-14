package com.shyamfuture.tantujayarnbank.data.model.profile

import com.google.gson.annotations.SerializedName

class GetProfileRequestModel(
    @SerializedName("user_id")
    val user_id: Int
) {
}