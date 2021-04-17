package com.amrdeveloper.currencyexchange.data

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface HistoryService {

    @GET("timeseries")
    fun getHistoryRates(
            @Query("start_date") start: String,
            @Query("end_date") end: String,
            @Query("base") base : String,
            @Query("symbols") symbols : String = "USD",
    ): Single<HistoryResponse>
}