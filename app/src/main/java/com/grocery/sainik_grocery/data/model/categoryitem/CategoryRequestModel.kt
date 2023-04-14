package com.grocery.sainik_grocery.data.model.categoryitem

import com.google.gson.annotations.SerializedName

data class CategoryRequestModel(
    @SerializedName("urc_id")
    val urc_id: String
)