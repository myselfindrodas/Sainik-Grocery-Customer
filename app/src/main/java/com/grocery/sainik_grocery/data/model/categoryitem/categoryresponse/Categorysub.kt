package com.grocery.sainik_grocery.data.model.categoryitem.categoryresponse


import com.google.gson.annotations.SerializedName

data class Categorysub(
    @SerializedName("id")
    val id: Int, // 2
    @SerializedName("image")
    val image: Any?, // null
    @SerializedName("name")
    val name: String // cleaning
)