package com.amrdeveloper.currencyexchange

import android.util.Log
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

private const val TAG = "HistoryRepository"

class HistoryRepository(private val historyService: HistoryService) {

    private val historyRates = MutableLiveData<HistoryResponse>()
    private val compositeDisposable = CompositeDisposable()

    fun loadHistoryRates(start : String, end : String, base : String) {
        val responseDisposable = historyService.getHistoryRates(start, end, base)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response -> historyRates.value = response },
                { t -> Log.d(TAG, "loadHistoryRates: ${t.message}") })

        compositeDisposable.add(responseDisposable)
    }

    fun getHistoryRates() = historyRates

    fun clearCompositeDisposable() {
        compositeDisposable.clear()
    }
}