package com.example.allweatherandroid.api

import com.example.allweatherandroid.data.Cities
import com.example.allweatherandroid.data.Weather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AllTheWeatherService {
    @GET("/cities")
    suspend fun getCities(
        @Query("search") citySearched: String,
        @Query("pageCount") numberReturned: Int = 1,
        @Query("pageNumber") startedCity: Int = 0
    ): Cities

    @GET("/cities/{id}")
    suspend fun getWeather(
    @Path("id") id: Int
    ): Weather
}