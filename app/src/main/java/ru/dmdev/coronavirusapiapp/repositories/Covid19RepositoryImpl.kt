package ru.dmdev.coronavirusapiapp.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.dmdev.coronavirusapiapp.api.CoronavirusApi
import ru.dmdev.coronavirusapiapp.extensions.toModel
import ru.dmdev.coronavirusapiapp.models.Country
import ru.dmdev.coronavirusapiapp.repositories.exceptions.RepositoryDataNotFoundException
import ru.dmdev.coronavirusapiapp.repositories.models.RepositoryResult
import javax.inject.Inject

class Covid19RepositoryImpl @Inject constructor(private val apiService: CoronavirusApi) : Covid19Repository {

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