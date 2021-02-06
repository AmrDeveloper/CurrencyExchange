package com.example.currencyexchange

import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RatesRepository(private val ratesService: RatesService) {

    private val latestRates = MutableLiveData<LatestResponse>()

    fun loadLatestRates() {
        ratesService.getLatestRates().enqueue(object : Callback<LatestResponse> {
            override fun onResponse(call: Call<LatestResponse>, response: Response<LatestResponse>) {
                if (response.code() == 200) {
                    latestRates.value = response.body()
                }
            }

            override fun onFailure(call: Call<LatestResponse>, t: Throwable) {

            }
        })
    }

    fun getLatestRates() = latestRates
}