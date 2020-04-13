package ru.dmdev.coronavirusapiapp.api

import com.squareup.okhttp.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import ru.dmdev.coronavirusapiapp.api.responses.SummaryResponse
import java.util.concurrent.TimeUnit


interface CoronavirusApi {
    @GET("summary")
    suspend fun getSummary(): SummaryResponse

    companion object Factory {
        fun create(): CoronavirusApi {

            val okHttpClient: okhttp3.OkHttpClient = okhttp3.OkHttpClient.Builder()
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
}