package com.amrdeveloper.currencyexchange

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RatesService {

    @GET("latest")
    fun getLatestRates(@Query("base") base : String): Call<LatestResponse>
}