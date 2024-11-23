package com.example.myapplication.ui.proekt

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.myapplication.data.model.FilmDetailsResponse
import com.example.myapplication.presentation.MoviesViewModel
import com.example.myapplication.presentation.ScreenState

@Composable
fun MovieDetailScreen(viewModel: MoviesViewModel, filmId: Int, navController: NavHostController) {

    val filmDetailsState by viewModel.filmDetailsState.collectAsState()

    LaunchedEffect(filmId) {
        viewModel.fetchFilmDetails(filmId)
    }

    when (val state = filmDetailsState) {
        is ScreenState.Loading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is ScreenState.Success<*> -> {
            val film = state.data as FilmDetailsResponse
            Column(
                modifier = Modifier.fillMaxSize().padding(16.dp)
            ) {
                Text(text = "Назад", Modifier.clickable { navController.popBackStack() })
                Image(
                    painter = rememberAsyncImagePainter(film.imageUrl),
                    contentDescription = film.title,
                    modifier = Modifier.fillMaxWidth().height(300.dp)
                )
                Text(text = film.title, fontWeight = FontWeight.Bold, fontSize = 24.sp)
                Text(text = "Описание: ${film.description}")
                Text(text = "Жанры: ${film.genres.joinToString { it.name }}")
                Text(text = "Актеры: ${film.actors.joinToString { it.name }}")
            }
        }
        is ScreenState.Error -> {
            Text(text = "Ошибка: ${state.message}")
        }
        else -> {}
    }
}