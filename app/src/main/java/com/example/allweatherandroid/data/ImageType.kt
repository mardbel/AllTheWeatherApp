package com.example.allweatherandroid.data

import com.google.gson.annotations.SerializedName

data class ImageType(@SerializedName("mdpiImageURL") val mdpi: String,
                     @SerializedName("xhdpiImageURL") val xhdpi: String,
                     @SerializedName("hdpiImageURL") val hdpi: String
                     )