package com.example.allweatherandroid.data

data class Days(val high: Int,
                val weatherType: String,
                val dayOfTheWeek: Int,
                val hourlyWeather: List<HourlyWeather>
)
