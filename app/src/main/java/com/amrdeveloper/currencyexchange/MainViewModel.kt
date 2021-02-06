package com.amrdeveloper.currencyexchange

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainViewModel(private val repository : RatesRepository) : ViewModel() {

    private val rates = repository.getLatestRates()

    fun loadLatestRates() {
        repository.loadLatestRates()
    }

    fun getRates() = rates
}

class MainViewModelFactory(private val repository: RatesRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}