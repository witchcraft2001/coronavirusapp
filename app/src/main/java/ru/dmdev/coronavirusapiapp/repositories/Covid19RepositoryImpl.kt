package ru.dmdev.coronavirusapiapp.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.dmdev.coronavirusapiapp.api.CoronavirusApi
import ru.dmdev.coronavirusapiapp.extensions.toModel
import ru.dmdev.coronavirusapiapp.models.Country
import ru.dmdev.coronavirusapiapp.repositories.exceptions.RepositoryDataNotFoundException
import ru.dmdev.coronavirusapiapp.repositories.models.RepositoryResult

class Covid19RepositoryImpl() : Covid19Repository {
    private var apiService = CoronavirusApi.create()
    override suspend fun getCovid19Statistics(): RepositoryResult<List<Country>> {
        return try {
            val response = withContext(Dispatchers.IO) {
                apiService.getSummaryAsync();
            }
            when {
                response.countries.isNotEmpty() -> RepositoryResult.Success(response.countries.map { it.toModel() })
                else -> RepositoryResult.Error(RepositoryDataNotFoundException("Not found"))
            }
        } catch (ex: Throwable) {
            RepositoryResult.Error(ex)
        }
    }
}