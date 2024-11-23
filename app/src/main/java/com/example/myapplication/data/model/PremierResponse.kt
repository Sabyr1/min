package com.example.myapplication.data.model

import com.google.gson.annotations.SerializedName


data class PremieresResponse(
    val total: Int,
    val items: List<Item>
)