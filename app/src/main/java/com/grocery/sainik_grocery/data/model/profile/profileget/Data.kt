package com.grocery.sainik_grocery.data.model.profile.profileget


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("user")
    val user: User
)