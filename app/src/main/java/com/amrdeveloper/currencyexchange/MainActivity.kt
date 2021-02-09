package com.amrdeveloper.currencyexchange

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.amrdeveloper.currencyexchange.databinding.ActivityMainBinding

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var ratesAdapter: RatesAdapter
    private lateinit var binding: ActivityMainBinding

    private val mainViewModel: MainViewModel by viewModels {
        val application = (application as MainApplication)
        MainViewModelFactory(application.ratesRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ratesAdapter = RatesAdapter()
        binding.ratesRecyclerview.adapter = ratesAdapter
        binding.ratesRecyclerview.layoutManager = LinearLayoutManager(this)
        binding.ratesRecyclerview.setHasFixedSize(true)

        mainViewModel.getRates().observe(this, {
            ratesAdapter.submitList(it.rates.toList())
        })

        mainViewModel.loadLatestRates()
    }
}