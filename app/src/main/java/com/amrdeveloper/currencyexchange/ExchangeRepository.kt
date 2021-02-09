package com.amrdeveloper.currencyexchange

import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExchangeRepository(private val exchangeService: ExchangeService) {

    private val exchangeRates = MutableLiveData<LatestResponse>()

    fun loadExchangeRates(base: String, symbol: String) {
        exchangeService.getExchangeRates(base, symbol).enqueue(object : Callback<LatestResponse> {
            override fun onResponse(call: Call<LatestResponse>, response: Response<LatestResponse>) {
                if (response.code() == 200) {
                    exchangeRates.value = response.body()
                }
            }

            override fun onFailure(call: Call<LatestResponse>, t: Throwable) {

            }
        })
    }

    fun getExchangeRates() = exchangeRates
}