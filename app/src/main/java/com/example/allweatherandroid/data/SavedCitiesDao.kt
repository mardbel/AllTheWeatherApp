package com.example.allweatherandroid.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SavedCitiesDao {

    @Query("SELECT * FROM SavedCities")
    suspend fun getAllSavedCities(): List<SavedCities>

    @Query("SELECT * FROM SavedCities WHERE id = :id")
    suspend fun getCityById(id: Int): SavedCities

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCity(city: SavedCities)

    @Delete
    suspend fun deleteCity(city: SavedCities)


}