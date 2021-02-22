package com.amrdeveloper.currencyexchange

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.amrdeveloper.currencyexchange.databinding.ActivityMainBinding
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var ratesAdapter: RatesAdapter
    private lateinit var binding: ActivityMainBinding
    private val currencies by lazy { resources.getStringArray(R.array.Currencies) }

    private val mainViewModel: MainViewModel by viewModels {
        val application = (application as MainApplication)
        MainViewModelFactory(application.ratesRepository, application.historyRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ratesRecyclerViewSetup()
        ratesChartSetup()

        mainViewModel.getHistory().observe(this, {
            val historyRatesResponse = it.rates.toSortedMap()
            val historyRates = historyRatesResponse.values
            
            val values = arrayListOf<Entry>()
            repeat(6) { i ->
                values.add(Entry(i.toFloat(), historyRates.elementAt(i)["USD"]!!.toFloat()))
            }

            val historyRatesDates = arrayListOf<String>()
            repeat(6) { i ->
                historyRatesDates.add(historyRatesResponse.keys.elementAt(i))
            }

            val lineDataSet = LineDataSet(values, "USD Rates with base ${it.base}")
            lineDataSet.fillAlpha = 110
            lineDataSet.color = Color.RED
            lineDataSet.valueTextColor = ContextCompat.getColor(this, R.color.purple_200)
            lineDataSet.mode = LineDataSet.Mode.HORIZONTAL_BEZIER
            lineDataSet.setDrawFilled(true)
            lineDataSet.fillColor = Color.GRAY

            val dataSet = arrayListOf<ILineDataSet>()
            dataSet.add(lineDataSet)

            val xAxis = binding.ratesChart.xAxis
            xAxis.valueFormatter = XAxisValueFormatter(historyRatesDates.toList())

            val lineData = LineData(dataSet)
            lineData.setDrawValues(true)

            binding.ratesChart.data = lineData
            binding.ratesChart.invalidate()
        })

        mainViewModel.getRates().observe(this, {
            ratesAdapter.submitList(it.rates.toList())
        })

        mainViewModel.loadLatestRates()
        mainViewModel.loadHistoryRates("EUR")
    }

    private fun ratesRecyclerViewSetup() {
        ratesAdapter = RatesAdapter()
        binding.ratesRecyclerview.adapter = ratesAdapter
        binding.ratesRecyclerview.layoutManager = LinearLayoutManager(this)
        binding.ratesRecyclerview.setHasFixedSize(true)
    }

    private fun ratesChartSetup() {
        binding.ratesChart.isDragEnabled = true
        binding.ratesChart.setScaleEnabled(true)
        binding.ratesChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        binding.ratesChart.axisLeft.textColor = Color.WHITE
        binding.ratesChart.axisRight.textColor = Color.WHITE
        binding.ratesChart.xAxis.textColor = Color.WHITE
        binding.ratesChart.xAxis.labelRotationAngle = -45f
        binding.ratesChart.xAxis.labelCount = 5
        binding.ratesChart.legend.textColor = ContextCompat.getColor(this, R.color.purple_200)
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

        override fun onQueryTextSubmit(currencyName: String?): Boolean {
            if(!currencyName.isNullOrEmpty() || currencyName?.length == 3 || currencies.contains(currencyName)) {
                mainViewModel.loadLatestRates(currencyName.toString())
                mainViewModel.loadHistoryRates(currencyName.toString())
            } else {
                Toast.makeText(baseContext, "Unsupported Currency name", Toast.LENGTH_SHORT).show()
            }
            return false
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            return false
        }
    }

    private class XAxisValueFormatter(private val values : List<String>) : ValueFormatter() {

        override fun getFormattedValue(value: Float): String {
            return values.elementAt(value.toInt())
        }
    }
}