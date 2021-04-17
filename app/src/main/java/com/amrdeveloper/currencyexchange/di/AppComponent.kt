package com.amrdeveloper.currencyexchange.di

import android.content.Context
import com.amrdeveloper.currencyexchange.exchange.di.ExchangeComponent
import com.amrdeveloper.currencyexchange.rates.di.MainComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppSubcomponents::class,
    NetworkModule::class,
    ViewModelBuilderModule::class
])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun mainComponent(): MainComponent.Factory
    fun exchangeComponent(): ExchangeComponent.Factory
}