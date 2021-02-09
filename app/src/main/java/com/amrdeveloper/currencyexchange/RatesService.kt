package com.amrdeveloper.currencyexchange

import retrofit2.Call
import retrofit2.http.GET

interface RatesService {

    @GET("latest")
    fun getLatestRates(): Call<LatestResponse>
}