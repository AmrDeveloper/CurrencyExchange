package com.amrdeveloper.currencyexchange.data

import io.reactivex.Single

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

private const val TAG = "RatesRepository"

class RatesRepository @Inject constructor(private val ratesService: RatesService) {

    fun loadLatestRates(base : String) : Single<LatestResponse> {
        return ratesService.getLatestRates(base)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}