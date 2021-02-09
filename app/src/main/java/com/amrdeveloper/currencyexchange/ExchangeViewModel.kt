package com.amrdeveloper.currencyexchange

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ExchangeViewModel(private val repository: ExchangeRepository) : ViewModel() {

    private val exchangeRates = repository.getExchangeRates()

    fun requestExchangeRates(base : String, symbol: String) {
        repository.loadExchangeRates(base, symbol)
    }

    fun getExchangeRates() = exchangeRates

}

class ExchangeViewModelFactory(private val repository: ExchangeRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ExchangeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ExchangeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}