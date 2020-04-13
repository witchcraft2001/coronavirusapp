package ru.dmdev.coronavirusapiapp.models

import ru.dmdev.coronavirusapiapp.R
import ru.dmdev.coronavirusapiapp.adapters.IBaseListItem
import java.util.*

data class Country(
    val country: String,
    val countryCode: String,
    val slug: String,
    val newConfirmed: Int,
    val totalConfirmed: Int,
    val newDeaths: Int,
    val totalDeaths: Int,
    val newRecovered: Int,
    val totalRecovered: Int,
    val date: String
) : IBaseListItem {
    override fun getLayoutId() = R.layout.layout_item
}