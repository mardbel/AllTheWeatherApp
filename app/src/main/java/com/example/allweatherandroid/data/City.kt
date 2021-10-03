package com.example.allweatherandroid.data

import com.google.gson.annotations.SerializedName

data class City(val name: String,
                @SerializedName("geonameid") val id: Int,
                @SerializedName("modification date") val lastUpdate: String,
                @SerializedName("imageURLs") val image: CityImage?)