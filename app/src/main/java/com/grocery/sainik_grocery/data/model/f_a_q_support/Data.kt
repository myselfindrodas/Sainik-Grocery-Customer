package com.grocery.sainik_grocery.data.model.f_a_q_support


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("appContent")
    val appContent: List<AppContent>
)