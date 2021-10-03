package com.example.allweatherandroid.di

import android.content.Context
import androidx.room.Room
import com.example.allweatherandroid.data.CitiesDb
import com.example.allweatherandroid.data.SavedCitiesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)

class LocalModule {

    @Provides fun providesCitiesDb(@ApplicationContext context: Context): CitiesDb {
        return Room.databaseBuilder(context, CitiesDb::class.java, "savedCities").build()
    }

    @Provides fun providesSavedCitiesDao(citiesDb: CitiesDb): SavedCitiesDao {
        return citiesDb.savedCitiesDao()
    }
}