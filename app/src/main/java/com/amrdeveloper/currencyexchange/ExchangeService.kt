package com.amrdeveloper.currencyexchange

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ExchangeService {

    @GET("latest")
    fun getExchangeRates(
            @Query("base") base : String,
            @Query("symbols") symbols : String
    ): Single<LatestResponse>
}