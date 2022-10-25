package com.example.moviedb.presentation.movie_details

import android.R
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.moviedb.presentation.home.HomeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.moviedb.utils.Constants
import com.google.gson.Gson
import org.w3c.dom.Text


@Composable
fun MovieDetails(navController: NavController, viewModel: DetailsViewModel = hiltViewModel()) {

    Log.e("apiCheck1", Gson().toJson(viewModel.movieDetails))
    Log.e("apiCheck2", Gson().toJson(viewModel.castList))

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp



    Box(Modifier.fillMaxSize()) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(Constants.IMAGE_BASE_URL + viewModel.movieDetails.value.backdropPath)
                .crossfade(true)
                .build(),
            contentDescription = stringResource(R.string.copy),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .size(screenHeight / 2.3f)
        )

        Card(
            Modifier
                .fillMaxSize()
                .background(Color.Transparent)
                .padding(top = screenHeight / 2.4f),
            shape = RoundedCornerShape(18.dp)
        ) {

            Column(Modifier.background(Color.Transparent)) {
                Text(text = "Overview", color = Color.Black, fontSize = 24.sp, modifier = Modifier.padding(top=150.dp, start = 20.dp, end = 20.dp))
                Text(text = viewModel.movieDetails.value.overview?:"", color = Color.Gray, fontSize = 16.sp, modifier = Modifier.padding(top=10.dp, start = 20.dp))
            }

        }

        Row(Modifier.padding(top = screenHeight / 3.2f).background(Color.Transparent)) {

            Card(
                shape = RoundedCornerShape(10.dp), modifier = Modifier
                    .width(160.dp)
                    .height(200.dp)
                    .padding(start = 20.dp)
            ) {

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(Constants.IMAGE_BASE_URL + viewModel.movieDetails.value.posterPath)
                        .crossfade(true)
                        .build(),
                    contentDescription = stringResource(R.string.copy),
                    contentScale = ContentScale.FillWidth)

            }

            Column(Modifier.padding(top = 90.dp)) {

                Text(text = viewModel.movieDetails.value.originalTitle?:"", color = Color.Black, fontSize = 24.sp, modifier = Modifier.padding(start = 20.dp, bottom = 8.dp))

                Row() {
                    Icon(painter = painterResource(id = com.example.moviedb.R.drawable.ic_calendar), contentDescription ="calendar", modifier = Modifier.padding(start = 20.dp), tint = Color.Gray )
                    Text(text = viewModel.movieDetails.value.releaseDate?:"", color = Color.Gray, fontSize = 15.sp, modifier = Modifier.padding(start = 8.dp, top = 2.dp))

                }

            }


        }
    }


}