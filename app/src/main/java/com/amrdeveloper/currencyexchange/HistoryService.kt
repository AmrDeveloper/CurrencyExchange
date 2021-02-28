package com.amrdeveloper.currencyexchange

import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface HistoryService {

    @GET("history")
    fun getHistoryRates(
            @Query("start_at") start: String,
            @Query("end_at") end: String,
            @Query("base") base : String,
            @Query("symbols") symbols : String = "USD"
    ): Single<HistoryResponse>
}