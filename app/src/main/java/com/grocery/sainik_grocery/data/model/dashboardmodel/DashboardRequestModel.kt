package com.grocery.sainik_grocery.data.model.dashboardmodel

import com.google.gson.annotations.SerializedName

data class DashboardRequestModel(
    @SerializedName("urc_id")
    val urc_id: String,
    @SerializedName("keywords")
    var keywords: String=""
)