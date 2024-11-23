package com.example.myapplication.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.myapplication.data.model.MovieItem

@Composable
fun MovieCard(movie: MovieItem, navController: NavHostController) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clickable {

                navController.navigate("movie_detail/${movie.id}")

            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .size(150.dp)
                .clip(RoundedCornerShape(8.dp)),
            shape = RoundedCornerShape(8.dp)
        ) {
            Image(
                painter = rememberImagePainter(
                    data = movie.imageUrl,
                    builder = {
                        crossfade(true)
                    }
                ),
                contentDescription = movie.title,
                modifier = Modifier.fillMaxSize()
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = movie.title,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
        Text(
            text = "Жанр: ${movie.genre.joinToString(", ") { it.name }}",
            fontSize = 14.sp,
            color = Color.Gray
        )
        Text(
            text = "Рейтинг: ${movie.rating}",
            fontSize = 14.sp,
            color = Color.Blue
        )
    }
}
@Composable
fun MovieSection(
    movies: List<MovieItem>,
    navController: NavHostController,
    onMovieClick: (MovieItem) -> Unit
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        items(movies) { movie ->
            MovieCard(movie = movie,navController = navController)
        }
    }
}