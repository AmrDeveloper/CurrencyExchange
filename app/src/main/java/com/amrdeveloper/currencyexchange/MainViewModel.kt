package com.amrdeveloper.currencyexchange

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.time.LocalDate

class MainViewModel(private val ratesRepository: RatesRepository,
                    private val historyRepository: HistoryRepository) : ViewModel() {

    private val rates = ratesRepository.getLatestRates()
    private val history = historyRepository.getHistoryRates()

    fun loadLatestRates(base: String = "USD") {
        ratesRepository.loadLatestRates(base)
    }

    fun loadHistoryRates(base: String) {
        val currentDate = LocalDate.now().toString()
        val startDate = LocalDate.now().minusDays(10).toString()
        historyRepository.loadHistoryRates(startDate, currentDate, base)
    }

    fun getRates() = rates
    fun getHistory() = history

    override fun onCleared() {
        super.onCleared()
        ratesRepository.clearCompositeDisposable()
        historyRepository.clearCompositeDisposable()
    }
}

class MainViewModelFactory(private val ratesRepository: RatesRepository,
                           private val historyRepository: HistoryRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(ratesRepository, historyRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}