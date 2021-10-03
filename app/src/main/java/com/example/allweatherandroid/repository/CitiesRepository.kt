package com.example.allweatherandroid.repository

import com.example.allweatherandroid.api.AllTheWeatherService
import com.example.allweatherandroid.data.SavedCities
import com.example.allweatherandroid.data.SavedCitiesDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CitiesRepository @Inject constructor(
    private val savedCitiesDao: SavedCitiesDao,
    private val remoteService: AllTheWeatherService
) {

    suspend fun getCity(searchedCity: String): SavedCities? {

        val cities = remoteService.getCities(searchedCity.trim())
        val savedCities = cities.toSavedCities()
        savedCities?.let { savedCitiesDao.insertCity(it) }
        return savedCities
    }

    suspend fun delete(cities: SavedCities) {
        savedCitiesDao.deleteCity(cities)
    }

    suspend fun getSavedCities() : List<SavedCities> {
        return savedCitiesDao.getAllSavedCities()
    }

    suspend fun getCityById(id: Int) : SavedCities {
        return savedCitiesDao.getCityById(id)
    }
}