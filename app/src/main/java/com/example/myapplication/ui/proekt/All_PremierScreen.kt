package com.example.myapplication.ui.proekt

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.data.model.Item
import com.example.myapplication.presentation.MovieCard
import com.example.myapplication.presentation.MoviesViewModel
import com.example.myapplication.presentation.ScreenState

@Composable
fun AllPremierScreen(
    viewModel: MoviesViewModel,
    navController: NavHostController
) {
    val premierScreen by viewModel.top250MoviesState.collectAsState()

    PremierScreen(viewModel = viewModel , 2022, "January")
    }

