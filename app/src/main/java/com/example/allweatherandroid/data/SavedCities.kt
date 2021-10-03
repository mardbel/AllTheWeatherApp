package com.example.allweatherandroid.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity
data class SavedCities(@PrimaryKey val id: Int,
                       val name: String,
                       val lastUpdate: String,
                       val mdpi: String,
                       val xhdpi: String,
                       val hdpi: String)