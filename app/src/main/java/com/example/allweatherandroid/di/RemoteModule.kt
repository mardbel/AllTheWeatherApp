package com.example.allweatherandroid.di

import com.example.allweatherandroid.api.AllTheWeatherService
import com.example.allweatherandroid.api.RetrofitServiceBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.converter.gson.GsonConverterFactory

import retrofit2.Retrofit

import okhttp3.OkHttpClient

import okhttp3.logging.HttpLoggingInterceptor





@Module
@InstallIn(SingletonComponent::class)

class RemoteModule {

    @Provides fun providesAllTheWeatherService(retrofit: Retrofit): AllTheWeatherService {
        return retrofit.create(AllTheWeatherService::class.java)
    }
@Provides
    fun providesRetrofitClient(): Retrofit{
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BASIC

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)


        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
        return retrofit
    }
}

const val BASE_URL = "https://weather.exam.bottlerocketservices.com"