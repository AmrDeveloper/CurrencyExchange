package com.amrdeveloper.currencyexchange

import com.google.gson.annotations.SerializedName

data class HistoryResponse(

    @SerializedName("rates")
    val rates: Map<String, Map<String, Double>>,

    @SerializedName("base")
    val base: String
)
