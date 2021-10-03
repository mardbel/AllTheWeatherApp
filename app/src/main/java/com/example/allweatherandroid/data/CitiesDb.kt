package com.example.allweatherandroid.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [SavedCities::class],
    version = 1
)
abstract class CitiesDb : RoomDatabase(){

 abstract fun savedCitiesDao(): SavedCitiesDao
}