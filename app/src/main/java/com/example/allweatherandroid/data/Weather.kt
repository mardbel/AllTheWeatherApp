package com.example.allweatherandroid.data

import com.google.gson.annotations.SerializedName

data class Weather(
    @SerializedName("city") val city: City,
    @SerializedName("weather") val weatherSpecification: WeatherSpecification
)