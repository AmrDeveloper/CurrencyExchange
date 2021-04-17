package com.amrdeveloper.currencyexchange.data

import android.util.Log
import androidx.lifecycle.MutableLiveData

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

private const val TAG = "RatesRepository"

class RatesRepository @Inject constructor(private val ratesService: RatesService) {

    private val latestRates = MutableLiveData<LatestResponse>()
    private val compositeDisposable = CompositeDisposable()

    fun loadLatestRates(base : String) {
        val responseDisposable = ratesService.getLatestRates(base)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response -> latestRates.value = response },
                        { t -> Log.d(TAG, "loadLatestRates: ${t.message}") })
        compositeDisposable.add(responseDisposable)
    }

    fun getLatestRates() = latestRates

    fun clearCompositeDisposable() {
        compositeDisposable.clear()
    }
}