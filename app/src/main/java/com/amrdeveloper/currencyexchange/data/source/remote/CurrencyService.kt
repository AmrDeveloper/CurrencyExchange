package com.amrdeveloper.currencyexchange.data.source.remote

import com.amrdeveloper.currencyexchange.data.HistoryResponse
import com.amrdeveloper.currencyexchange.data.LatestResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyService {

    @GET("latest")
    fun getLatestRates(
            @Query("base") base: String)
    : Single<LatestResponse>

    @GET("latest")
    fun getExchangeRates(
            @Query("base") base : String,
            @Query("symbols") symbols : String,)
    : Single<LatestResponse>

    @GET("timeseries")
    fun getHistoryRates(
            @Query("start_date") start: String,
            @Query("end_date") end: String,
            @Query("base") base : String,
            @Query("symbols") symbols : String = "USD",
    ): Single<HistoryResponse>
}