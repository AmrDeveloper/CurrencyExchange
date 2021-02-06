package com.amrdeveloper.currencyexchange

import android.app.Application

class MainApplication : Application() {

    private val ratesService by lazy { RatesService.create() }
    val ratesRepository by lazy { RatesRepository(ratesService) }

}