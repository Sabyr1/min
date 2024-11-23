package com.example.myapplication.data.model

import com.google.gson.annotations.SerializedName

data class FilmDetailsResponse(
    @SerializedName("filmId") val id : Int,
    @SerializedName("nameRu") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("posterUrl") val imageUrl: String,
    @SerializedName("genres") val genres: List<Genre>,
    @SerializedName("persons") val actors: List<Actor>
)