package com.amrdeveloper.currencyexchange.rates.di

import androidx.lifecycle.ViewModel
import com.amrdeveloper.currencyexchange.di.ViewModelKey
import com.amrdeveloper.currencyexchange.rates.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindViewModel(viewModel : MainViewModel) : ViewModel
}