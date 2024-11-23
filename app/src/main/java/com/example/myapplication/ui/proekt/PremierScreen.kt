package com.example.myapplication.ui.proekt
import androidx.compose.foundation.Image

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.material3.Text
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.ui.draw.clip
import coil.compose.rememberImagePainter
import com.example.myapplication.data.model.Item

@Composable
fun PremierScreen(viewModel: MoviesViewModel, year: Int, month: String) {
    val premieresState by viewModel.premieresState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchPremieres(year, month)
    }

    when (val state = premieresState) {
        is ScreenState.Loading -> {
            Text("Загрузка...", modifier = Modifier.padding(16.dp))
        }
        is ScreenState.Success<*> -> {
            val premieres = state.data as List<Item>
            if (premieres.isNotEmpty()) {

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(8.dp)
                ) {
                    items(premieres) { premiere ->
                        MovieCards( premiere = premiere )
                    }
                }
            } else {
                Text("Нет доступных премьер", modifier = Modifier.padding(16.dp))
            }
        }
        is ScreenState.Error -> {
            Text("Ошибка: ${state.message}", color = Color.Red, modifier = Modifier.padding(16.dp))
        }
        else -> Unit
    }
}
@Composable
fun MovieCards(premiere: Item) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clickable {


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
                data = premiere.posterUrl),
                contentDescription = premiere.nameRu,
                modifier = Modifier.fillMaxSize()
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = premiere.nameRu,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
        Text(
            text = "Жанры: ${premiere.genres.joinToString(", ") { it.name }}",
            fontSize = 14.sp,
            color = Color.Gray
        )
        Text(
            text = "Рейтинг: ${premiere.kinopoiskId}",
            fontSize = 14.sp,
            color = Color.Blue
        )
    }
}