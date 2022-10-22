package com.example.moviedb.presentation.home

import android.R
import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bumptech.glide.Glide
import com.example.moviedb.ui.theme.LightWhite
import com.example.moviedb.ui.theme.Purple200
import com.example.moviedb.ui.theme.ThinGrey
import com.example.moviedb.ui.theme.WhiteTransparent
import com.example.moviedb.utils.Constants
import com.google.gson.Gson
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds


@Composable
fun Home(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {

    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

    val backgroundImageName = remember {
        mutableStateOf(Constants.IMAGE_BASE_URL + viewModel.nowPlayingMovieList.getOrNull(0)?.posterPath)
    }



    Box(
        Modifier
            .fillMaxSize()
            .background(LightWhite)) {

        GlideImage(
            imageModel = { Constants.IMAGE_BASE_URL + backgroundImageName.value },
            Modifier
                .blur(radius = 10.dp)
                .fillMaxWidth()
                .height(screenHeight / 2)
        )


        Column {

            SearchBar(screenHeight)
            Text(
                text = "Now Playing",
                modifier = Modifier.padding(24.dp),
                color = Color.White,
                fontSize = 24.sp
            )
            NowPlayingList(viewModel) {
                backgroundImageName.value = it
            }


        }


    }
}

@Composable
fun SearchBar(screenHeight: Dp) {

    val search = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .padding(bottom = screenHeight / 32, top = 30.dp), verticalArrangement = Arrangement.Top
    ) {

        Card(
            Modifier
                .fillMaxWidth()
                .height(90.dp)
                .padding(20.dp),
            backgroundColor = WhiteTransparent,
            shape = RoundedCornerShape(30.dp)
        )
        {
            Row(
                Modifier
                    .fillMaxWidth()
                    .background(WhiteTransparent),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_menu_search),
                    contentDescription = "search icon",
                    Modifier.padding(start = 10.dp),
                    tint = Color.White
                )
                TextField(
                    value = search.value,
                    onValueChange = {
                        search.value = it
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.Gray,
                        disabledTextColor = Color.Transparent,
                        backgroundColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    placeholder = { Text(text = "Search Movies", color = Color.White) },
                    singleLine = true
                )
            }


        }

    }
}


@Composable
fun NowPlayingList(viewModel: HomeViewModel, imageUrl: (String) -> Unit) {


    LazyRow {

        items(
            items = viewModel.nowPlayingMovieList,
            key = { message ->
                message.id.toString()
            }
        ) { message ->
            Card(
                Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .height(400.dp), shape = RoundedCornerShape(10.dp)
            ) {

                GlideImage(
                    Constants.IMAGE_BASE_URL.let {
                        { it + message.posterPath }
                    },
                    modifier = Modifier.fillMaxSize(),
                    imageOptions = ImageOptions(
                        contentScale = ContentScale.Inside,
                        alignment = Alignment.Center
                    )

                )

            }
            message.posterPath?.let { imageUrl(it) }

        }
    }

}