package com.example.allweatherandroid.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.allweatherandroid.data.SavedCities
import com.example.allweatherandroid.repository.CitiesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val repository: CitiesRepository)
    : ViewModel() {

    init {
        viewModelScope.launch {
            var citiesFromRepo = repository.getSavedCities()
            _cities.postValue(citiesFromRepo)
        }
    }

    private val _cities = MutableLiveData<List<SavedCities>>()
    val cities: LiveData<List<SavedCities>>
        get() = _cities


}