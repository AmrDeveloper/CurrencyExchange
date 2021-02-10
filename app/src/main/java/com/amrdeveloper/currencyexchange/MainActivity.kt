package com.amrdeveloper.currencyexchange

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.amrdeveloper.currencyexchange.databinding.ActivityMainBinding

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var ratesAdapter: RatesAdapter
    private lateinit var binding: ActivityMainBinding
    private val currencies by lazy { resources.getStringArray(R.array.Currencies) }

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        val menuItem = menu?.findItem(R.id.search_menu)
        val searchView = menuItem?.actionView as SearchView
        searchView.queryHint = "Currency Name"
        searchView.isSubmitButtonEnabled = false
        searchView.setOnQueryTextListener(searchViewQueryListener)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.exchange_menu -> {
                val intent = Intent(this, ExchangeActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private val searchViewQueryListener = object : SearchView.OnQueryTextListener {

        override fun onQueryTextSubmit(query: String?): Boolean {
            if(!query.isNullOrEmpty() || query?.length == 3 || currencies.contains(query)) {
                mainViewModel.loadLatestRates(query.toString())
            } else {
                Toast.makeText(baseContext, "Unsupported Currency name", Toast.LENGTH_SHORT).show()
            }
            return false
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            return false
        }
    }
}