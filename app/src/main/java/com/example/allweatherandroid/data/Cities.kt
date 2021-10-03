package com.example.allweatherandroid.data

import com.google.gson.annotations.SerializedName


data class Cities(
    @SerializedName("startIndex") val startIndex: Int,
    @SerializedName("cities") val cities: List<City>,
    @SerializedName("totalCitiesFound") val totalCitiesFound: Int,
) {

    fun toSavedCities():SavedCities?{
      val firstCity = cities.firstOrNull() ?: return null
        return SavedCities(firstCity.id, firstCity.name,
            firstCity.lastUpdate,
            firstCity.image?.androidImageURLs?.mdpi.orEmpty(),
            firstCity.image?.androidImageURLs?.xhdpi.orEmpty(),
            firstCity.image?.androidImageURLs?.hdpi.orEmpty())
    }
}