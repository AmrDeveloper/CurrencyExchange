package com.amrdeveloper.currencyexchange.di

import com.amrdeveloper.currencyexchange.data.ExchangeService
import com.amrdeveloper.currencyexchange.data.HistoryService
import com.amrdeveloper.currencyexchange.data.RatesService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    private val BASE_URL = "https://api.exchangerate.host/"

    @Singleton
    @Provides
    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    fun provideRatesService(retrofit: Retrofit) : RatesService {
        return retrofit.create(RatesService::class.java)
    }

    @Singleton
    @Provides
    fun provideExchangeService(retrofit: Retrofit) : ExchangeService {
        return retrofit.create(ExchangeService::class.java)
    }

    @Singleton
    @Provides
    fun provideHistoryService(retrofit: Retrofit) : HistoryService {
        return retrofit.create(HistoryService::class.java)
    }
}