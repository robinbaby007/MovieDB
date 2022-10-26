package com.example.moviedb.presentation.movie_details

import android.R
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.moviedb.presentation.home.HomeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.moviedb.presentation.home.Loading
import com.example.moviedb.utils.Constants
import com.google.gson.Gson
import dagger.hilt.android.internal.Contexts
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.util.*


@Composable
fun MovieDetails(viewModel: DetailsViewModel = hiltViewModel()) {

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
            LazyColumn {
                item {

                    Text(
                        text = "Overview",
                        color = Color.Black,
                        fontSize = 22.sp,
                        modifier = Modifier.padding(top = 150.dp, start = 20.dp, end = 20.dp),
                        style = MaterialTheme.typography.h1
                    )
                    Text(
                        text = viewModel.movieDetails.value.overview ?: "",
                        color = Color.Gray,
                        fontSize = 15.sp,
                        modifier = Modifier.padding(top = 10.dp, start = 20.dp, end = 10.dp),
                        style = MaterialTheme.typography.subtitle1,
                        lineHeight = 20.sp
                    )

                    Text(
                        text = "Top Bill Cast",
                        color = Color.Black,
                        fontSize = 22.sp,
                        modifier = Modifier.padding(top = 10.dp, start = 20.dp, end = 20.dp),
                        style = MaterialTheme.typography.h1

                    )

                    LazyRow(Modifier.padding(top = 10.dp)) {

                        items(
                            items = viewModel.castList,
                            key = { message ->
                                message.toString()
                            }
                        ) { item ->

                            Column {

                                AsyncImage(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data(Constants.IMAGE_BASE_URL + item.profilePath)
                                        .crossfade(true)
                                        .placeholder(com.example.moviedb.R.drawable.avatar)
                                        .error(com.example.moviedb.R.drawable.avatar)
                                        .build(),
                                    contentDescription = stringResource(R.string.copy),
                                    contentScale = ContentScale.FillWidth,
                                    modifier = Modifier
                                        .size(100.dp)
                                        .padding(10.dp)
                                        .clip(RoundedCornerShape(150.dp)),
                                )

                                Text(
                                    text = item.name ?: "",
                                    color = Color.Black,
                                    fontSize = 14.sp,
                                    modifier = Modifier
                                        .padding(horizontal = 10.dp)
                                        .width(100.dp),
                                    style = MaterialTheme.typography.subtitle1,
                                    textAlign = TextAlign.Center

                                )

                            }


                        }

                    }

                }


            }
        }

        Row(
            Modifier
                .padding(top = screenHeight / 3.2f)
                .background(Color.Transparent)
        ) {

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
                    contentScale = ContentScale.FillWidth
                )

            }

            Column(
                Modifier
                    .padding(top = 90.dp)
                    .background(Color.White)
                    .fillMaxWidth()
                    .height(110.dp)
            ) {

                Text(
                    text = viewModel.movieDetails.value.originalTitle ?: "",
                    color = Color.Black,
                    fontSize = 22.sp,
                    modifier = Modifier.padding(start = 20.dp, bottom = 8.dp),
                    style = MaterialTheme.typography.h1
                )
                Row() {
                    Icon(
                        painter = painterResource(id = com.example.moviedb.R.drawable.ic_calendar),
                        contentDescription = "calendar",
                        modifier = Modifier.padding(start = 20.dp),
                        tint = Color.Gray
                    )
                    Text(
                        text = dateFormatter(viewModel.movieDetails.value.releaseDate),
                        color = Color.DarkGray,
                        fontSize = 15.sp,
                        modifier = Modifier.padding(start = 8.dp, top = 2.dp),
                        style = MaterialTheme.typography.h1

                    )

                }

            }


        }
        Loading(viewModel.apiCompleteMap)
    }


}

fun dateFormatter(date: String?): String {

    return try {

        val parser = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val formatter = SimpleDateFormat("dd MMMM yyyy", Locale.US)
        return date?.let { parser.parse(it)?.let { formatter.format(it) } } ?: "Not Available"

    } catch (e: Exception) {
        "Not Available"
    }

}