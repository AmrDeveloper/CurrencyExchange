package com.amrdeveloper.currencyexchange.rates

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.amrdeveloper.currencyexchange.data.CurrencyConstants
import com.amrdeveloper.currencyexchange.databinding.ListItemRatesBinding

class RatesAdapter : ListAdapter<Pair<String, Double>, RecyclerView.ViewHolder>(RatesDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = ListItemRatesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RatesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val rate = getItem(position)
        (holder as RatesViewHolder).bind(rate)
    }

    class RatesViewHolder(
            private var binding: ListItemRatesBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(rate : Pair<String, Double>) {
            binding.nameText.text = rate.first
            binding.ratesText.text = rate.second.toString()
            binding.fullnameText.text = CurrencyConstants.getCurrencyFullName(rate.first)
            binding.flagImage.setImageResource(CurrencyConstants.getCurrencyDrawableId(rate.first))
        }
    }

}

private class RatesDiffCallback : DiffUtil.ItemCallback<Pair<String, Double>>() {

    override fun areItemsTheSame(oldItem: Pair<String, Double>, newItem: Pair<String, Double>): Boolean {
        return oldItem.first == newItem.first
    }

    override fun areContentsTheSame(oldItem: Pair<String, Double>, newItem: Pair<String, Double>): Boolean {
        return oldItem == newItem
    }

}