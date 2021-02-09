package com.amrdeveloper.currencyexchange

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.amrdeveloper.currencyexchange.databinding.ActivityExchangeBinding

private const val TAG = "ExchangeActivity"

class ExchangeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityExchangeBinding

    private val exchangeViewModel: ExchangeViewModel by viewModels {
        val application = (application as MainApplication)
        ExchangeViewModelFactory(application.exchangeRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
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
                binding.symbolText.text = rate.second.times(times).toString()
            }
        })

        binding.exchangeButton.setOnClickListener {
            val times = binding.baseEdit.text
            if(times.isEmpty()) {
                Toast.makeText(this, "Times mustn't be empty", Toast.LENGTH_SHORT).show()
            }else {
                val base = binding.baseSpinner.selectedItem.toString()
                var symbol = binding.symbolSpinner.selectedItem.toString()
                exchangeViewModel.requestExchangeRates(base, symbol)
            }
        }
    }
}