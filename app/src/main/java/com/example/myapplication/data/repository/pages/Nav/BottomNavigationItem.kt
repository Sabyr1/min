package com.example.myapplication.data.repository.pages.Nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Search
import androidx.compose.ui.graphics.vector.ImageVector


sealed class BottomNavigationItem(
    val title: String,
    val route: String,
    val icon: ImageVector
){
    data object Homepage: BottomNavigationItem("Home", "main_screen", Icons.Rounded.Home)
    data object Search: BottomNavigationItem("Search", "search", Icons.Rounded.Search)
    data object Profile: BottomNavigationItem("Profile", "profile", Icons.Rounded.Person)
}