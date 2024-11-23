package com.example.myapplication.data

import com.example.myapplication.data.model.MovieItem
import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    @SerializedName("items") val movies: List<MovieItem>
)
