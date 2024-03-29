package com.amrdeveloper.currencyexchange.rates

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.amrdeveloper.currencyexchange.data.source.CurrencyRepository
import com.amrdeveloper.currencyexchange.data.HistoryResponse
import com.amrdeveloper.currencyexchange.data.LatestResponse
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

private const val TAG = "MainViewModel"

class MainViewModel @Inject constructor(
        application: Application,
        private val currencyRepository: CurrencyRepository)
    : AndroidViewModel(application) {

    private val latestRates = MutableLiveData<LatestResponse>()
    private val historyRates = MutableLiveData<HistoryResponse>()
    private val compositeDisposable = CompositeDisposable()

    fun loadLatestRates(base: String = "USD") {
        val disposable = currencyRepository.loadLatestRates(base)
                .subscribe({ response -> latestRates.value = response },
                        { Toast.makeText(getApplication(), "Can't load latest rates", Toast.LENGTH_SHORT).show() })
        compositeDisposable.add(disposable)
    }

    fun loadHistoryRates(base: String) {
        val disposable = currencyRepository.loadHistoryRates(base)
                .subscribe({ response -> historyRates.value = response },
                        { Toast.makeText(getApplication(), "Can't Load History Rates", Toast.LENGTH_SHORT).show() })
        compositeDisposable.add(disposable)
    }

    fun getRates() = latestRates
    fun getHistory() = historyRates

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}