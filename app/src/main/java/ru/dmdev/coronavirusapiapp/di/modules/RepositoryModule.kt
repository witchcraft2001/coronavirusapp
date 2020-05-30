package ru.dmdev.coronavirusapiapp.di.modules

import dagger.Module
import dagger.Provides
import ru.dmdev.coronavirusapiapp.api.CoronavirusApi
import ru.dmdev.coronavirusapiapp.repositories.Covid19Repository
import ru.dmdev.coronavirusapiapp.repositories.Covid19RepositoryImpl
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideCovid19Repository(coronavirusApi: CoronavirusApi): Covid19Repository {
        return Covid19RepositoryImpl(coronavirusApi)
    }
}