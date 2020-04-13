package ru.dmdev.coronavirusapiapp.api.responses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.util.*

@JsonClass(generateAdapter = true)
data class SummaryResponse(
    @field:Json(name = "Global")
    val global: GlobalResponse,
    @field:Json(name = "Countries")
    val countries: List<CountryResponse>,
    @field:Json(name = "Date")
    val date: String
)
