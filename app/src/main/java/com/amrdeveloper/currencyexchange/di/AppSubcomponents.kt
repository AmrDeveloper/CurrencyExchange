package com.amrdeveloper.currencyexchange.di

import com.amrdeveloper.currencyexchange.exchange.di.ExchangeComponent
import com.amrdeveloper.currencyexchange.rates.di.MainComponent
import dagger.Module

@Module(subcomponents = [
    MainComponent::class,
    ExchangeComponent::class
])
class AppSubcomponents