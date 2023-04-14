package com.grocery.sainik_grocery.data.model.filter


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("attribute")
    val attribute: List<Attribute>
)