package com.example.currencyexchange

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RatesService {

    @GET("latest")
    fun getLatestRates(): Call<LatestResponse>

    companion object {

        private const val BASE_URL = "https://api.exchangeratesapi.io/"

        fun create(): RatesService {
            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BASE_URL)
                    .build()

            return retrofit.create(RatesService::class.java)
        }
    }
}