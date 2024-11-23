package com.example.myapplication.ui.proekt

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.myapplication.data.model.Item
import com.example.myapplication.data.model.MovieItem
import com.example.myapplication.data.repository.pages.Nav.BottomNavigationBar
import com.example.myapplication.presentation.MovieCard
import com.example.myapplication.presentation.MoviesViewModel
import com.example.myapplication.presentation.ScreenState
import com.example.myapplication.presentation.SectionHeader

@Composable
fun NextScreen(
    viewModel: MoviesViewModel,
    navController: NavHostController
) {
    val top250MoviesState by viewModel.top250MoviesState.collectAsState()
    val premieresState by viewModel.premieresState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "SkillCinema",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 16.dp)
        )


        SectionHeader("TOP 250")
        Button(onClick = {

            navController.navigate("all_movies")
        }) {
            Text(text = "ВСЕ")
        }

        when (val state = top250MoviesState) {
            is ScreenState.Loading -> {
                Text("Загрузка...", modifier = Modifier.padding(16.dp))
            }
            is ScreenState.Success<*> -> {
                val movies = state.data as List<MovieItem>
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(8.dp)
                ) {
                    items(movies.take(5)) { movie ->
                        MovieCard(movie = movie, navController = navController)
                    }
                }
            }
            is ScreenState.Error -> {
                Text("Ошибка: ${state.message}", color = Color.Red, modifier = Modifier.padding(16.dp))
            }

            else -> Unit
        }

        Spacer(modifier = Modifier.height(24.dp))


        SectionHeader("Premieres")

        Button(onClick = {

            navController.navigate("all_premieres")
        }) {
            Text(text = "ВСЕ")
        }
        when (val state = premieresState) {
            is ScreenState.Loading -> {
                Text("Загрузка...", modifier = Modifier.padding(16.dp))
            }
            is ScreenState.Success<*> -> {
                val premieres = state.data as List<Item>
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(8.dp)
                ) {
                    items(premieres.take(5)) { premiere ->
                        MovieCards(premiere = premiere)
                    }
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
        viewModel.fetchPremieres(year = 2024, month = "January")
    }
}

@Composable
fun SectionHeader(title: String) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Button(onClick = {

        }) {
            Text(text = "ВСЕ")
        }
    }
}








