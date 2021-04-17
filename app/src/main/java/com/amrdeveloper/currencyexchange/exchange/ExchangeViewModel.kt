package com.amrdeveloper.currencyexchange.exchange

import androidx.lifecycle.ViewModel
import com.amrdeveloper.currencyexchange.data.ExchangeRepository
import javax.inject.Inject

class ExchangeViewModel @Inject constructor(
        private val repository: ExchangeRepository)
    : ViewModel() {

    private val exchangeRates = repository.getExchangeRates()

    fun requestExchangeRates(base: String, symbol: String) {
        repository.loadExchangeRates(base, symbol)
    }

    fun getExchangeRates() = exchangeRates

    override fun onCleared() {
        super.onCleared()
        repository.clearCompositeDisposable()
    }
}