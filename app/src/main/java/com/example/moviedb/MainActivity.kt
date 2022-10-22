package com.example.moviedb

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.moviedb.data.retrofit.MovieDBInterface
import com.example.moviedb.presentation.home.Home
import com.example.moviedb.presentation.movie_details.MovieDetails
import com.example.moviedb.ui.theme.LightWhite
import com.example.moviedb.ui.theme.MovieDBTheme
import com.example.moviedb.ui.theme.Purple200
import com.example.moviedb.utils.Screens
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Scope

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieDBTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val systemUiController = rememberSystemUiController()
                    DisposableEffect(systemUiController, true) {
                        systemUiController.setSystemBarsColor(
                            color = LightWhite,
                            darkIcons = true
                        )
                        onDispose {}
                    }
                    MovieDB()
                }
            }
        }
    }
}

@Composable
fun MovieDB() {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.HomeScreen.route) {

        composable(Screens.HomeScreen.route) {
            Home(navController)
        }

        composable(Screens.MovieDetailsScreen.route + "?movieId={movieId}", arguments = listOf(
            navArgument(name = "movieId") {
                type = NavType.StringType
                defaultValue = ""
            }
        )) {
            MovieDetails(navController)
        }

    }

}

