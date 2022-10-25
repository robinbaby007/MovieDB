package com.example.moviedb.utils

sealed class Screens(val route: String) {
    object HomeScreen : Screens("Home")
    object MovieDetailsScreen : Screens("Details")

}