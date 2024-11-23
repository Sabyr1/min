package com.example.myapplication.data

import android.media.Image
import com.example.myapplication.data.model.Film
import com.example.myapplication.data.model.FilmDetailsResponse
import com.example.myapplication.data.model.Images
import com.example.myapplication.data.model.Movie
import com.example.myapplication.data.model.PremieresResponse
import com.example.myapplication.data.model.Staff
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface KinopoiskApiService {
    @Headers("X-API-KEY: 55a29254-7f72-4c93-9d0c-e4fdd7b59094")
    @GET("v2.2/films/collections")
    suspend fun getCollections(
        @Query("type") type: String,
        @Query("page") page: Int
    ): Response<MoviesResponse>

    @Headers("X-API-KEY: 55a29254-7f72-4c93-9d0c-e4fdd7b59094")
    @GET("v2.2/films/{id}")
    suspend fun getFilmDetails(
        @Path("id") id: Int = 1
    ): Response<FilmDetailsResponse>

    @Headers("X-API-KEY: 55a29254-7f72-4c93-9d0c-e4fdd7b59094")
    @GET("v2.2/films/collections")
    suspend fun getPremier(
        @Query("year") year: Int,
        @Query("month") month: String
    ): Response<PremieresResponse>
}





















//interface KinopoiskApi {
//
//    @GET("/api/v2.2/films/{id}")
//    @Headers("X-API-KEY: 55a29254-7f72-4c93-9d0c-e4fdd7b59094")
//    suspend fun getMovie(
//        @Path("id") id: Int,
//    ): Movie
//
//    @GET("/api/v2.2/films/collections")
//    @Headers("X-API-KEY: 55a29254-7f72-4c93-9d0c-e4fdd7b59094")
//    suspend fun getMoviesByCollection(
//        @Query("type") type: MoviesCollectionType,
//        @Query("page") page: Int = 1
//    ): CollectionMovieDto
//
//    @GET("/api/v2.2/films/{id}")
//    @Headers("X-API-KEY: 55a29254-7f72-4c93-9d0c-e4fdd7b59094")
//    suspend fun getMovieById(
//        @Path("id") id: Int,
//    ): DetailMovie
//
//    @GET("/api/v1/staff")
//    @Headers("X-API-KEY: 55a29254-7f72-4c93-9d0c-e4fdd7b59094")
//    suspend fun getActors(
//        @Query("filmId") filmId: Int,
//    ): List<Actors>
//
//    @GET("/api/v1/staff/{id}")
//    @Headers("X-API-KEY: 55a29254-7f72-4c93-9d0c-e4fdd7b59094")
//    suspend fun getStaffDetails(
//        @Path("id") id: Int,
//    ): Staff
//
//
//    @GET("/api/v2.2/films/{id}/images")
//    @Headers("X-API-KEY: 55a29254-7f72-4c93-9d0c-e4fdd7b59094")
//    suspend fun getImages(
//        @Path("id") id: Int,
//    ): Images
//
//}
//val retrofit = Retrofit.Builder()
//    .addConverterFactory(GsonConverterFactory.create())
//    .baseUrl("https://kinopoiskapiunofficial.tech")
//    .build()




























//interface FilmApi {
////    @Headers(
////        "X-API-KEY: 55a29254-7f72-4c93-9d0c-e4fdd7b59094",
////        "Content-Type: application/json"
////    )
////    @GET("/api/v2.2/films/collections")
////    suspend fun getCollection(): Collections
////
////    @Headers(
////        "X-API-KEY: 55a29254-7f72-4c93-9d0c-e4fdd7b59094",
////        "Content-Type: application/json"
////    )
////    @GET("/api/v2.2/films/collections?type=TOP_POPULAR_ALL&page=1")
////    suspend fun getCollectionTop_All(): Collections
////
////    @Headers(
////        "X-API-KEY: 55a29254-7f72-4c93-9d0c-e4fdd7b59094",
////        "Content-Type: application/json"
////    )
////    @GET("/api/v2.2/films/{id}")
////    suspend fun getFilmById(@Path("id") id: Int): Film
////
////
////    @Headers(
////        "X-API-KEY: 55a29254-7f72-4c93-9d0c-e4fdd7b59094",
////        "Content-Type: application/json"
////    )
////    @GET("api/v1/staff/{id}")
////    suspend fun getActorDetailById(
////        @Path("id") id: Int ) : Actor
////
////
////}