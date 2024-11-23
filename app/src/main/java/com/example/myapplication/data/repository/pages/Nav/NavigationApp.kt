package com.example.myapplication.data.repository.pages.Nav

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.presentation.MoviesViewModel
import com.example.myapplication.ui.proekt.AllMoviesScreen
import com.example.myapplication.ui.proekt.AllPremierScreen
import com.example.myapplication.ui.proekt.MovieDetailScreen
import com.example.myapplication.ui.proekt.NextScreen
import com.example.myapplication.ui.proekt.PremierScreen
import com.example.myapplication.ui.proekt.SkillCinemaScreen

@Composable
fun NavigationApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "main_screen") {
        composable("main_screen") { SkillCinemaScreen(navController) }
        composable("profile") { Profile() }
        composable("search") { Search() }
        composable("next_screen") {
            val viewModel: MoviesViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
            NextScreen(viewModel = viewModel, navController = navController)
        }
        composable("all_movies") {
            val viewModel: MoviesViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
            AllMoviesScreen(viewModel = viewModel, navController = navController)
        }
        composable("all_premieres") {
            val viewModel: MoviesViewModel = viewModel()
            AllPremierScreen(viewModel = viewModel , navController = navController)

        }
        composable("premieres_screen") {
            val viewModel: MoviesViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
            PremierScreen(viewModel = viewModel, year=2022, month = "January")
        }
        composable("movie_detail/{filmId}") { backStackEntry ->
            val filmId = backStackEntry.arguments?.getString("filmId")?.toIntOrNull()

            println("Извлеченный filmId из backStackEntry: $filmId")

            if (filmId == null || filmId < 1) {
                Text(
                    text = "Некорректный ID фильма: $filmId",
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    color = Color.Red
                )
            } else {

                val viewModel: MoviesViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
                MovieDetailScreen(
                    viewModel = viewModel,
                    filmId = filmId,
                    navController = navController
                )
            }
        }
    }
}

