package com.example.allweatherandroid.data

data class HourlyWeather(val weatherType: String,
                         val hour: Int,
                         val temperature: Int,
                         val rainChance: Double,
                         val windSpeed: Double,
                         val humidity: Double)
