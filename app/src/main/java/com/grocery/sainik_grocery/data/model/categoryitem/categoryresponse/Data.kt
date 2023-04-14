package com.grocery.sainik_grocery.data.model.categoryitem.categoryresponse


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("category")
    val category: List<Category>
)