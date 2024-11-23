package com.example.myapplication.data.model

data class Images(
    val total: Int,
    val totalPages: Int,
    val items: List<Image>
)

data class Image(
    val imageUrl: String,
    val previewUrl: String
)