package com.amrdeveloper.currencyexchange.rates

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amrdeveloper.currencyexchange.data.HistoryRepository
import com.amrdeveloper.currencyexchange.data.HistoryResponse
import com.amrdeveloper.currencyexchange.data.LatestResponse
import com.amrdeveloper.currencyexchange.data.RatesRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

private const val TAG = "MainViewModel"

class MainViewModel @Inject constructor(
        private val ratesRepository: RatesRepository,
        private val historyRepository: HistoryRepository)
    : ViewModel() {

    private val latestRates = MutableLiveData<LatestResponse>()
    private val historyRates = MutableLiveData<HistoryResponse>()
    private val compositeDisposable = CompositeDisposable()

    fun loadLatestRates(base: String = "USD") {
        val disposable = ratesRepository.loadLatestRates(base)
                .subscribe({ response -> latestRates.value = response },
                        { t -> Log.d(TAG, "loadLatestRates: ${t.message}") })
        compositeDisposable.add(disposable)
    }

    fun loadHistoryRates(base: String) {
        val disposable = historyRepository.loadHistoryRates(base)
                            .subscribe({ response -> historyRates.value = response },
                                    { t -> Log.d(TAG, "loadHistoryRates: ${t.message}") })
        compositeDisposable.add(disposable)
    }

    fun getRates() = latestRates
    fun getHistory() = historyRates

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}