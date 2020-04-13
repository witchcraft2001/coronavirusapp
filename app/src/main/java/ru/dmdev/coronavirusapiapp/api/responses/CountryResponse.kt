package ru.dmdev.coronavirusapiapp.api.responses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ru.dmdev.coronavirusapiapp.models.Country
import java.util.*

@JsonClass(generateAdapter = true)
data class CountryResponse(
    @field:Json(name = "Country")
    val country: String,
    @field:Json(name = "CountryCode")
    val countryCode: String,
    @field:Json(name = "Slug")
    val slug: String,
    @field:Json(name = "NewConfirmed")
    val newConfirmed: Int,
    @field:Json(name = "TotalConfirmed")
    val totalConfirmed: Int,
    @field:Json(name = "NewDeaths")
    val newDeaths: Int,
    @field:Json(name = "TotalDeaths")
    val totalDeaths: Int,
    @field:Json(name = "NewRecovered")
    val newRecovered: Int,
    @field:Json(name = "TotalRecovered")
    val totalRecovered: Int,
    @field:Json(name = "Date")
    val date: String
)