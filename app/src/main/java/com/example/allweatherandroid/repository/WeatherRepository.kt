package com.example.allweatherandroid.repository

import com.example.allweatherandroid.api.AllTheWeatherService
import com.example.allweatherandroid.data.Weather
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepository @Inject constructor(
    private val remoteService: AllTheWeatherService
){

    suspend fun getWeather(id: Int): Weather {
        return remoteService.getWeather(id)
    }
}