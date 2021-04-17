package com.amrdeveloper.currencyexchange.exchange.di

import androidx.lifecycle.ViewModel
import com.amrdeveloper.currencyexchange.rates.MainViewModel
import com.amrdeveloper.currencyexchange.di.ViewModelKey
import com.amrdeveloper.currencyexchange.exchange.ExchangeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ExchangeModule {

    @Binds
    @IntoMap
    @ViewModelKey(ExchangeViewModel::class)
    abstract fun bindViewModel(viewModel : ExchangeViewModel) : ViewModel
}