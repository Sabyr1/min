package com.example.myapplication.ui.proekt

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.myapplication.data.model.MovieItem
import com.example.myapplication.presentation.MovieCard
import com.example.myapplication.presentation.MoviesViewModel
import com.example.myapplication.presentation.ScreenState
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items

@Composable
fun AllMoviesScreen(
    viewModel: MoviesViewModel,
    navController: NavHostController
) {
    val top250MoviesState by viewModel.top250MoviesState.collectAsState()
    val selectedGenres = remember { mutableStateListOf<String>() }
    val ratingFilter = remember { mutableStateOf(0.0) }
    val genres = listOf("Все жанры", "Фэнтези", "Драма", "Экшн", "Комедия")


    val filteredMovies = remember(top250MoviesState, selectedGenres, ratingFilter.value) {
        when (val state = top250MoviesState) {
            is ScreenState.Success<*> -> {
                val movies = state.data as List<MovieItem>

                val genreFiltered = if (selectedGenres.isNotEmpty() && selectedGenres.contains("Все жанры").not()) {
                    movies.filter { movie -> movie.genre.any { selectedGenres.contains(it.name) } }
                } else {
                    movies
                }

                genreFiltered.filter { movie -> movie.rating >= ratingFilter.value }
            }
            else -> emptyList()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Все фильмы",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )

Row{
        Text("Выберите жанр:", modifier = Modifier .padding(16.dp),  fontSize = 16.sp)
        genres.forEach { genre ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = selectedGenres.contains(genre),
                    onCheckedChange = { isChecked ->
                        if (isChecked) {
                            selectedGenres.add(genre)
                        } else {
                            selectedGenres.remove(genre)
                        }
                    }
                )
                Text(text = genre)
            }
        }
    }
        Spacer(modifier = Modifier.height(16.dp))


        Text("Выберите минимальный рейтинг:", fontSize = 16.sp)
        Slider(
            value = ratingFilter.value.toFloat(),
            onValueChange = { ratingFilter.value = it.toDouble() },
            valueRange = 0f..10f,
            steps = 9,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))


        when (val state = top250MoviesState) {
            is ScreenState.Loading -> {
                Text("Загрузка...", modifier = Modifier.padding(16.dp))
            }
            is ScreenState.Success<*> -> {
                val movies = filteredMovies
                if (movies.isNotEmpty()) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2), // 2 items per row
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(8.dp) )
                    {
                        items(movies) { movie ->
                            MovieCard(movie = movie, navController = navController)
                        }
                    }
                } else {
                    Text("Нет доступных фильмов", modifier = Modifier.padding(16.dp))
                }
            }
            is ScreenState.Error -> {
                Text("Ошибка: ${state.message}", color = Color.Red, modifier = Modifier.padding(16.dp))
            }
            else -> Unit
        }
    }

    LaunchedEffect(Unit) {
        viewModel.fetchTop250Movies()
    }
}