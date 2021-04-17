package com.amrdeveloper.currencyexchange.data

import com.google.gson.annotations.SerializedName

data class LatestResponse(

        @SerializedName("base")
        val base: String,

        @SerializedName("rates")
        val rates: Map<String, Double>
)