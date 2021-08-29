package com.amrdeveloper.currencyexchange.exchange

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.amrdeveloper.currencyexchange.data.source.CurrencyRepository
import com.amrdeveloper.currencyexchange.data.LatestResponse
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

private const val TAG = "ExchangeViewModel"

class ExchangeViewModel @Inject constructor(
        application: Application,
        private val repository: CurrencyRepository)
    : AndroidViewModel(application) {

    private val exchangeRates = MutableLiveData<LatestResponse>()
    private val compositeDisposable = CompositeDisposable()

    fun loadExchangeRates(base: String, symbol: String) {
        val disposable = repository.loadExchangeRates(base, symbol)
                .subscribe({ response -> exchangeRates.value = response },
                        { Toast.makeText(getApplication(), "Can't Load Exchange Rates", Toast.LENGTH_SHORT).show() })
        compositeDisposable.add(disposable)
    }

    fun getExchangeRates() = exchangeRates

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}