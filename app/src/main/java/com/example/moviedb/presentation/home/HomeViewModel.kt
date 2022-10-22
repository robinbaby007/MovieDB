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
import androidx.compose.ui.unit.Constraints
import com.example.moviedb.domain.use_cases.ListNowPlayingMovies
import com.example.moviedb.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class HomeViewModel @Inject constructor(useCases: UseCases) : ViewModel() {

    private val _nowPlayingFullData = mutableStateOf(NowPlayingMovieResponse())
    private val nowPlayingFullData: State<NowPlayingMovieResponse> = _nowPlayingFullData
    private var _nowPlayingMovieList = mutableStateListOf<NowPlayingMovieResponse.Result>()
    val nowPlayingMovieList :List<NowPlayingMovieResponse.Result> = _nowPlayingMovieList


    init {

        viewModelScope.launch {
            useCases.listNowPlayingMovies.invoke(Constants.LANG, "1").collect {

                it.body()?.let { response ->
                    _nowPlayingFullData.value = response
                    _nowPlayingMovieList.clear()
                    response.results?.forEach { result ->
                        _nowPlayingMovieList.add(result!!)
                    }
                }

            }
        }

    }

}