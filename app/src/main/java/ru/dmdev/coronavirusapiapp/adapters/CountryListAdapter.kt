package ru.dmdev.coronavirusapiapp.adapters

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import kotlinx.android.synthetic.main.layout_item.view.*
import ru.dmdev.coronavirusapiapp.R
import ru.dmdev.coronavirusapiapp.models.Country

class CountryListAdapter : SimpleListAdapter() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        return when (viewType) {
            R.layout.layout_item -> CountryViewHolder(inflateByViewType(context, viewType, parent))
            else -> throw IllegalStateException("There is no match with current layoutId")
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder) {
            is CountryViewHolder -> {
                val countryItem = items[position] as Country
                holder.name.text = countryItem.country
                holder.newConfirmed.text = countryItem.newConfirmed.toString()
                holder.totalConfirmed.text = countryItem.totalConfirmed.toString()
                holder.totalDeaths.text = countryItem.totalDeaths.toString()
                holder.totalRecovered.text = countryItem.totalRecovered.toString()
            }
            else -> throw IllegalStateException("There is no match with current holder instance")
        }
    }

    class CountryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = view.textCountryName
        val newConfirmed = view.textCountryNewConfirmed
        val totalConfirmed = view.textCountryTotal
        val totalDeaths = view.textCountryTotalDeaths
        val totalRecovered = view.textCountryTotalRecovered
    }
}