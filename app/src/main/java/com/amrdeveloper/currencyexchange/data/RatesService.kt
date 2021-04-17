package com.amrdeveloper.currencyexchange.data

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RatesService {

    @GET("latest")
    fun getLatestRates(
            @Query("base") base : String,
    ): Single<LatestResponse>
}