package ru.dmdev.coronavirusapiapp.extensions

import ru.dmdev.coronavirusapiapp.api.responses.CountryResponse
import ru.dmdev.coronavirusapiapp.models.Country

fun CountryResponse.toModel() = Country(country, countryCode, slug, newConfirmed, totalConfirmed, newDeaths, totalDeaths, newRecovered, totalRecovered, date)