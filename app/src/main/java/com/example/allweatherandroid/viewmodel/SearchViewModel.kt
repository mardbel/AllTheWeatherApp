package com.example.allweatherandroid.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.allweatherandroid.data.SavedCities
import com.example.allweatherandroid.repository.CitiesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val repository: CitiesRepository) : ViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler {
            _, error -> _state.postValue(State.Failure(error)) }

    private val _state = MutableLiveData<State>()
    val state: LiveData<State>
    get() = _state

    fun getRemoteCities(city: String) {
        _state.value = State.Loading()
        viewModelScope.launch(exceptionHandler + IO) {
            var city = repository.getCity(city)
            if (city == null) {
                throw CityNotFoundedException()
            } else {
                _state.postValue(State.Success(city))
            }
        }
    }

    sealed class State{
        class Success(val city: SavedCities) : State()
        class Failure(val cause: Throwable) : State()
        class Loading() :State()
    }

    class CityNotFoundedException : Exception("You need to provide a valid location")
}