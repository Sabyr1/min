package com.example.myapplication.presentation


sealed interface ScreenState {
    object Initial : ScreenState
    object Loading : ScreenState
    data class Success<T>(val data: T) : ScreenState
    data class Error(val message: String) : ScreenState
}