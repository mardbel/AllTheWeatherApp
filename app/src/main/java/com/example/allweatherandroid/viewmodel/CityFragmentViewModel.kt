package com.example.allweatherandroid.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.allweatherandroid.data.SavedCities
import com.example.allweatherandroid.data.Weather
import com.example.allweatherandroid.repository.CitiesRepository
import com.example.allweatherandroid.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityFragmentViewModel @Inject constructor(private val cityRepository: CitiesRepository,
        private val weatherRepository: WeatherRepository) :
    ViewModel() {

    fun getCityById(id: Int){
        viewModelScope.launch {
            var cityById = cityRepository.getCityById(id)
            _cities.postValue(cityById)
            val weather = weatherRepository.getWeather(id)
            _weather.postValue(weather)
        }
    }

    private val _cities = MutableLiveData<SavedCities>()
    val cities: LiveData<SavedCities>
        get() = _cities

    private val _weather = MutableLiveData<Weather>()
    val weather: LiveData<Weather>
        get() = _weather

}