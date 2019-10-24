package com.pingo.moviebook.shared.network

import com.pingo.moviebook.BuildConfig
import com.pingo.moviebook.BuildConfig.BASE_URL
import com.pingo.moviebook.shared.network.api.MovieApiService
import com.pingo.moviebook.shared.network.interceptor.ApiKeyInterceptor
import com.pingo.moviebook.shared.network.interceptor.ConnectivityInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

val apiService = module {

    fun getApiService(retrofit: Retrofit) = retrofit.create(MovieApiService::class.java)

    single { getApiService(get()) }
}



val retrofitModule = module {

    fun getRetrofit(httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(httpClient)
            .build()
    }

    single {
        getRetrofit(get())
    }



    val connectionTimeOut = 10 * 1000
    val readTimeOut = 10 * 1000
    val writeTimeOut = 60 * 1000

    fun okHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        when {
            BuildConfig.DEBUG -> interceptor.level = HttpLoggingInterceptor.Level.BODY
            else -> interceptor.level = HttpLoggingInterceptor.Level.NONE
        }

        val userHttpClientBuilder = OkHttpClient.Builder()
            .readTimeout(readTimeOut.toLong(), TimeUnit.MILLISECONDS)
            .writeTimeout(writeTimeOut.toLong(), TimeUnit.MILLISECONDS)
            .addInterceptor(interceptor)
            .addInterceptor(ApiKeyInterceptor())
            .addInterceptor(ConnectivityInterceptor())
            .connectTimeout(connectionTimeOut.toLong(), TimeUnit.MILLISECONDS)

        return userHttpClientBuilder.build()
    }

    single { okHttpClient() }

}