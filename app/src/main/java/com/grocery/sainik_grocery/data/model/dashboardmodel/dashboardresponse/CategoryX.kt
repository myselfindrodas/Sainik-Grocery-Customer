package com.grocery.sainik_grocery.data.model.dashboardmodel.dashboardresponse


import com.google.gson.annotations.SerializedName

data class CategoryX(
    @SerializedName("id")
    val id: Int, // 2
    @SerializedName("image")
    val image: Any?, // null
    @SerializedName("name")
    val name: String // cleaning
)