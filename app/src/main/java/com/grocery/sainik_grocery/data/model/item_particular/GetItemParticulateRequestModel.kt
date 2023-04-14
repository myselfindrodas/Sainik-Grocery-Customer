package com.shyamfuture.tantujayarnbank.data.model.item_particular

import com.google.gson.annotations.SerializedName

class GetItemParticulateRequestModel(
    @SerializedName("user_id")
    val user_id: Int,
    @SerializedName("cat_id")
    val cat_id: Int
) {
}