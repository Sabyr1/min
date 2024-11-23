package com.example.myapplication.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.KinopoiskApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MoviesViewModel : ViewModel() {
    private val _top250MoviesState = MutableStateFlow<ScreenState>(ScreenState.Initial)
    val top250MoviesState: StateFlow<ScreenState> get() = _top250MoviesState

    private val _filmDetailsState = MutableStateFlow<ScreenState>(ScreenState.Initial)
    val filmDetailsState: StateFlow<ScreenState> get() = _filmDetailsState

    private val _premieresState = MutableStateFlow<ScreenState>(ScreenState.Initial)
    val premieresState: StateFlow<ScreenState> get() = _premieresState


    fun fetchTop250Movies() {


        viewModelScope.launch {
            _top250MoviesState.value = ScreenState.Loading
            try {
                val response = KinopoiskApi.retrofitService.getCollections("TOP_250_MOVIES", 1)
                if (response.isSuccessful) {
                    val movies = response.body()?.movies?.take(20) ?: emptyList()
                    movies.forEach { println("Полученный фильм: ID=${it.id}, Title=${it.title}") }
                    println("Полученные фильмы: $movies")
                    _top250MoviesState.value = ScreenState.Success(movies ?: emptyList())
                } else {
                    val errorBody = response.errorBody()?.string()
                    println("Ошибка сервера: ${response.code()} - $errorBody")
                    _top250MoviesState.value =
                        ScreenState.Error("Ошибка сервера: ${response.code()}")
                }
            } catch (e: Exception) {
                _top250MoviesState.value = ScreenState.Error("Ошибка сети: ${e.message}")
            }
        }
    }
    fun fetchPremieres(year: Int, month: String) {
        viewModelScope.launch {
            _premieresState.value = ScreenState.Loading
            try {
                val response = KinopoiskApi.retrofitService.getPremier(2005, month)
                if (response.isSuccessful) {
                    val premieres = response.body()?.items?.take(20)?: emptyList()
                    println("Updated premieres state with data: $premieres")
                    _premieresState.value = ScreenState.Success(premieres ?: emptyList())
                } else {
                    _premieresState.value = ScreenState.Error("Ошибка сервера: ${response.code()}")
                }
            } catch (e: Exception) {
                _premieresState.value = ScreenState.Error("Ошибка сети: ${e.message}")
            }
        }
    }
    fun fetchFilmDetails(id: Int) {
        viewModelScope.launch {
            _filmDetailsState.value = ScreenState.Loading
            try {
                val response = KinopoiskApi.retrofitService.getFilmDetails(id)
                if (response.isSuccessful) {
                    val filmDetails = response.body()
                    _filmDetailsState.value = ScreenState.Success(filmDetails)
                } else {
                    val errorBody = response.errorBody()?.string()
                    _filmDetailsState.value = ScreenState.Error("Ошибка сервера: ${response.code()} - $errorBody")
                }
            } catch (e: Exception) {
                _filmDetailsState.value = ScreenState.Error("Ошибка сети: ${e.message}")
            }
        }
    }

}

