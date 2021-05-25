package com.amrdeveloper.currencyexchange

import android.app.Application
import com.amrdeveloper.currencyexchange.di.AppComponent
import com.amrdeveloper.currencyexchange.di.DaggerAppComponent

open class MainApplication : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(this)
    }
}