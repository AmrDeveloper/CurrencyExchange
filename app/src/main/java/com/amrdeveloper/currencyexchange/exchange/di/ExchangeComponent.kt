package com.amrdeveloper.currencyexchange.exchange.di

import com.amrdeveloper.currencyexchange.exchange.ExchangeActivity
import dagger.Subcomponent

@Subcomponent(modules = [ExchangeModule::class])
interface ExchangeComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): ExchangeComponent
    }

    fun inject(activity: ExchangeActivity)
}