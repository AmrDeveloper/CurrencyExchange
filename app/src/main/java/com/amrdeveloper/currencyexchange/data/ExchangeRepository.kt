package com.amrdeveloper.currencyexchange.data

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

private const val TAG = "ExchangeRepository"

class ExchangeRepository @Inject constructor(private val exchangeService: ExchangeService) {

    fun loadExchangeRates(base: String, symbol: String) : Single<LatestResponse> {
       return exchangeService.getExchangeRates(base, symbol)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}