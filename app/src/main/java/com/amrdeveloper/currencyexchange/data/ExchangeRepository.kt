package com.amrdeveloper.currencyexchange.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

private const val TAG = "ExchangeRepository"

class ExchangeRepository @Inject constructor(private val exchangeService: ExchangeService) {

    private val exchangeRates = MutableLiveData<LatestResponse>()
    private val compositeDisposable = CompositeDisposable()

    fun loadExchangeRates(base: String, symbol: String) {
        val responseDisposable = exchangeService.getExchangeRates(base, symbol)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response -> exchangeRates.value = response },
                { t -> Log.d(TAG, "loadExchangeRates: ${t.message}") })
        compositeDisposable.add(responseDisposable)
    }

    fun getExchangeRates() = exchangeRates

    fun clearCompositeDisposable() {
        compositeDisposable.clear()
    }
}