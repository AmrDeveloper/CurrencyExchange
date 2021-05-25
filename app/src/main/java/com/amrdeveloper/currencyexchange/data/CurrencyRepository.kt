package com.amrdeveloper.currencyexchange.data

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.time.LocalDate
import javax.inject.Inject

private const val TAG = "CurrencyRepository"

class CurrencyRepository @Inject constructor(private val currencyService : CurrencyService) {

    fun loadLatestRates(base : String) : Single<LatestResponse> {
        return currencyService.getLatestRates(base)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun loadHistoryRates(base : String) : Single<HistoryResponse> {
        val currentDate = LocalDate.now().toString()
        val startDate = LocalDate.now().minusDays(10).toString()
        return currencyService.getHistoryRates(startDate, currentDate, base)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun loadExchangeRates(base: String, symbol: String) : Single<LatestResponse> {
        return currencyService.getExchangeRates(base, symbol)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

}