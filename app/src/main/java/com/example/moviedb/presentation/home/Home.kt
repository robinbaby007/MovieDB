package com.example.moviedb.presentation.home

import android.R
import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.compose.AsyncImage
import coil.request.ImageRequest
 import com.example.moviedb.ui.theme.LightWhite
import com.example.moviedb.ui.theme.WhiteTransparent
import com.example.moviedb.utils.Constants
import com.example.moviedb.utils.HomeBottomNavigation



@Composable
fun Home(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {

    /*val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val scrollState = rememberScrollState()*/


    val backgroundImageName = remember {
        mutableStateOf("")
    }

    Scaffold(bottomBar = { BottomNavigation(navController) }) {

        Box(
            Modifier
                .fillMaxSize()
                .background(LightWhite)
        ) {
            
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(Constants.IMAGE_BASE_URL + backgroundImageName.value)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(com.example.moviedb.R.drawable.place_holder),
                contentDescription = stringResource(R.string.copy),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .blur(30.dp)
                    .fillMaxSize()
            )

            LazyColumn(Modifier.padding(10.dp)) {

                item {
                    SearchBar()
                    Text(
                        text = "Now Playing",
                        modifier = Modifier.padding(start = 24.dp),
                        color = Color.White,
                        fontSize = 24.sp
                    )
                    NowPlayingList(viewModel) {
                        backgroundImageName.value = it
                    }
                    Text(
                        text = "Top Rated",
                        color = Color.White,
                        modifier = Modifier.padding(top = 10.dp, bottom = 20.dp, start = 24.dp),
                        fontSize = 24.sp
                    )
                    TopRated(viewModel)

                }

            }
        }

    }


}

@Composable
fun SearchBar() {

    val search = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent)
            .padding(bottom = 10.dp, top = 20.dp), verticalArrangement = Arrangement.Top
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


    LazyRow(Modifier.padding(top = 10.dp)) {

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

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(Constants.IMAGE_BASE_URL +message.posterPath)
                        .crossfade(true)
                        .build(),
                    contentDescription = stringResource(R.string.copy),
                    contentScale = ContentScale.Fit,
                 )

            }
            message.posterPath?.let { imageUrl(it) }

        }
    }

}

@Composable
fun BottomNavigation(navController: NavController) {

    val navigationItems = listOf(
        HomeBottomNavigation.Home,
        HomeBottomNavigation.Film,
        HomeBottomNavigation.TV
    )

    BottomNavigation(
        backgroundColor = Color.Black,
        contentColor = Color.White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        navigationItems.forEach {
            BottomNavigationItem(
                selected = it.route == currentRoute,
                onClick = {},
                icon = {
                    Icon(
                        painter = painterResource(id = it.icon),
                        contentDescription = it.title
                    )
                })

        }

    }

}

@Composable
fun TopRated(viewModel: HomeViewModel) {


    LazyRow {

        items(
            items = viewModel.topRatedMovieList,
            key = { message ->
                message.id.toString()
            }
        ) { message ->
            Card(
                Modifier
                    .padding(end = 10.dp, bottom = 60.dp)
                    .height(100.dp), shape = RoundedCornerShape(10.dp)
            ) {

                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(Constants.IMAGE_BASE_URL +message.posterPath)
                        .crossfade(true)
                        .build(),
                    contentDescription = stringResource(R.string.copy),
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.fillMaxSize()
                )

            }
        }
    }


}