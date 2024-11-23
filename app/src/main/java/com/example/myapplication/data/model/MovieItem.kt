package com.example.myapplication.data.model

import com.google.gson.annotations.SerializedName

data class MovieItem(
    @SerializedName("id") val id: Int,
    @SerializedName("nameRu") val title: String,
    @SerializedName("genres") val genre: List<Genre>,
    @SerializedName("posterUrlPreview") val imageUrl: String,
    @SerializedName("ratingKinopoisk") val rating: Double
)