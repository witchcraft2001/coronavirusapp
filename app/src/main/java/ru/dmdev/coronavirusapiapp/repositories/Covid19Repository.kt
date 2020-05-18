package ru.dmdev.coronavirusapiapp.repositories

import ru.dmdev.coronavirusapiapp.models.Country
import ru.dmdev.coronavirusapiapp.repositories.models.RepositoryResult

interface Covid19Repository {
    suspend fun getCovid19Statistics(): RepositoryResult<List<Country>>
}