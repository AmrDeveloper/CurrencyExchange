package com.amrdeveloper.currencyexchange.exchange

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.amrdeveloper.currencyexchange.MainApplication
import com.amrdeveloper.currencyexchange.databinding.ActivityExchangeBinding
import javax.inject.Inject

private const val TAG = "ExchangeActivity"

class ExchangeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExchangeBinding
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private val exchangeViewModel by viewModels<ExchangeViewModel> { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MainApplication).appComponent.exchangeComponent().create().inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityExchangeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        exchangeViewModel.getExchangeRates().observe(this, {
            val rates = it.rates.toList()
            if (rates.isEmpty()) {
                Toast.makeText(this, "Invalid Values", Toast.LENGTH_SHORT).show()
            } else {
                val rate = rates[0]
                val times = binding.baseEdit.text.toString().toInt()
                val exchangeRatesVal = rate.second.times(times)
                binding.symbolText.text = String.format("%.4f", exchangeRatesVal)
            }
        })

        binding.exchangeButton.setOnClickListener {
            val times = binding.baseEdit.text
            if (times.isEmpty()) {
                Toast.makeText(this, "Times mustn't be empty", Toast.LENGTH_SHORT).show()
            } else {
                val base = binding.baseSpinner.selectedItem.toString()
                val symbol = binding.symbolSpinner.selectedItem.toString()
                if (base == symbol) {
                    binding.symbolText.text = times
                } else {
                    exchangeViewModel.requestExchangeRates(base, symbol)
                }
            }
        }
    }
}