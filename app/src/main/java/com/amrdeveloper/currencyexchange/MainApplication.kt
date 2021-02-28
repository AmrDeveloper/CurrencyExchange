package com.amrdeveloper.currencyexchange

import android.app.Application
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainApplication : Application() {

    private val BASE_URL = "https://api.exchangeratesapi.io/"

    private val retrofit by lazy {
        Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    private val ratesService by lazy { retrofit.create(RatesService::class.java) }
    private val exchangeService by lazy { retrofit.create(ExchangeService::class.java) }
    private val historyService by lazy { retrofit.create(HistoryService::class.java) }

    val ratesRepository by lazy { RatesRepository(ratesService) }
    val exchangeRepository by lazy { ExchangeRepository(exchangeService) }
    val historyRepository by lazy { HistoryRepository(historyService) }
}