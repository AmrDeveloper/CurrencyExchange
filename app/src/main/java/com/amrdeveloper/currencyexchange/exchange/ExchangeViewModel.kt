package com.amrdeveloper.currencyexchange.exchange

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amrdeveloper.currencyexchange.data.ExchangeRepository
import com.amrdeveloper.currencyexchange.data.LatestResponse
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

private const val TAG = "ExchangeViewModel"

class ExchangeViewModel @Inject constructor(
        private val repository: ExchangeRepository)
    : ViewModel() {

    private val exchangeRates = MutableLiveData<LatestResponse>()
    private val compositeDisposable = CompositeDisposable()

    fun requestExchangeRates(base: String, symbol: String) {
        val disposable = repository.loadExchangeRates(base, symbol)
                .subscribe({ response -> exchangeRates.value = response },
                        { t -> Log.d(TAG, "loadExchangeRates: ${t.message}") })
        compositeDisposable.add(disposable)
    }

    fun getExchangeRates() = exchangeRates

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}