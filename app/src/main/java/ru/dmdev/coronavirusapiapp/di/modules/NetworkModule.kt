package ru.dmdev.coronavirusapiapp.di.modules

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import ru.dmdev.coronavirusapiapp.api.CoronavirusApi
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideCoronavirusApi(): CoronavirusApi {
        val okHttpClient = okhttp3.OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("https://api.covid19api.com/")
            .client(okHttpClient)
            .build()

        return retrofit.create(CoronavirusApi::class.java)
    }
}