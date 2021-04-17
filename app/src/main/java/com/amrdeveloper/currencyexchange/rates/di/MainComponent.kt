package com.amrdeveloper.currencyexchange.rates.di

import com.amrdeveloper.currencyexchange.rates.MainActivity
import dagger.Subcomponent

@Subcomponent(modules = [MainModule::class])
interface MainComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): MainComponent
    }

    fun inject(activity: MainActivity)
}