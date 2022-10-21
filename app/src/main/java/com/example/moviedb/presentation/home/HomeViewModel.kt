package com.example.moviedb.presentation.home

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviedb.data.retrofit.NowPlayingMovieResponse
import com.example.moviedb.domain.use_cases.UseCases
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject
import androidx.compose.runtime.State
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class HomeViewModel @Inject constructor(useCases: UseCases) : ViewModel() {

    private val _nowPlayingList = mutableStateOf(NowPlayingMovieResponse())
    val nowPlayingList : State<NowPlayingMovieResponse> =_nowPlayingList



    init {

        viewModelScope.launch {
            useCases.listNowPlayingMovies.invoke("", 1).collect() {

                it.body()?.let {response->
                    _nowPlayingList.value = response
                }

            }
        }

    }

}