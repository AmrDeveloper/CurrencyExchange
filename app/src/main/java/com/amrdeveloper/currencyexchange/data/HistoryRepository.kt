package com.amrdeveloper.currencyexchange.data

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.time.LocalDate
import javax.inject.Inject

private const val TAG = "HistoryRepository"

class HistoryRepository @Inject constructor(private val historyService: HistoryService) {

    fun loadHistoryRates(base : String) : Single<HistoryResponse> {
        val currentDate = LocalDate.now().toString()
        val startDate = LocalDate.now().minusDays(10).toString()
        return historyService.getHistoryRates(startDate, currentDate, base)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}