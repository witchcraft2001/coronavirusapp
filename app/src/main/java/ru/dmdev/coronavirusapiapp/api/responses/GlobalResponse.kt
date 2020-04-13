package ru.dmdev.coronavirusapiapp.api.responses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GlobalResponse (
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
    val totalRecovered: Int
)
