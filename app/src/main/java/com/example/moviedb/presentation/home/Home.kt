package com.example.moviedb.presentation.home

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.gson.Gson


@Composable
fun Home(navController: NavController,viewModel:HomeViewModel = hiltViewModel()){

    Log.e("showMe", Gson().toJson(viewModel.nowPlayingList.value))

}