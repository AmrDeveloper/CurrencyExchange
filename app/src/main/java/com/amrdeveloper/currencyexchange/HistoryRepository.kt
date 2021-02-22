package com.amrdeveloper.currencyexchange

import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoryRepository(private val historyService: HistoryService) {

    private val historyRates = MutableLiveData<HistoryResponse>()

    fun loadHistoryRates(start : String, end : String, base : String) {
        historyService.getHistoryRates(start, end, base).enqueue(object :
            Callback<HistoryResponse> {
            override fun onResponse(
                call: Call<HistoryResponse>,
                response: Response<HistoryResponse>
            ) {
                if (response.code() == 200) {
                    historyRates.value = response.body()
                }
            }

            override fun onFailure(call: Call<HistoryResponse>, t: Throwable) {

            }
        })

    }

    fun getHistoryRates() = historyRates
}