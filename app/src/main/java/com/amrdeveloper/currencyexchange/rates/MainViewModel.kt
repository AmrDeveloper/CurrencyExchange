package com.amrdeveloper.currencyexchange.rates

import androidx.lifecycle.ViewModel
import com.amrdeveloper.currencyexchange.data.HistoryRepository
import com.amrdeveloper.currencyexchange.data.RatesRepository
import java.time.LocalDate
import javax.inject.Inject

class MainViewModel @Inject constructor(
        private val ratesRepository: RatesRepository,
        private val historyRepository: HistoryRepository)
    : ViewModel() {

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