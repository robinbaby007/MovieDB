package com.example.moviedb.presentation.home

import android.util.Log
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
import androidx.compose.runtime.mutableStateMapOf
import com.example.moviedb.utils.Constants
import com.example.moviedb.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay

@HiltViewModel
class HomeViewModel @Inject constructor(useCases: UseCases) : ViewModel() {

    private val _nowPlayingFullData = mutableStateOf(NowPlayingMovieResponse())
    private val nowPlayingFullData: State<NowPlayingMovieResponse> = _nowPlayingFullData
    private var _nowPlayingMovieList = mutableStateListOf<NowPlayingMovieResponse.Result>()
    val nowPlayingMovieList: List<NowPlayingMovieResponse.Result> = _nowPlayingMovieList
    private var _topRatedMovieList = mutableStateListOf<NowPlayingMovieResponse.Result>()
    var topRatedMovieList: List<NowPlayingMovieResponse.Result> = _topRatedMovieList
    private var _apiCompleteMap = mutableStateMapOf<Int, Boolean>()
    val apiCompleteMap: Map<Int, Boolean> = _apiCompleteMap

    init {
        initMapValues()
        viewModelScope.launch {
            useCases.listNowPlayingMovies.invoke(Constants.LANG, "1").collect {
                when (it) {

                    is NetworkResult.Success -> {
                        it.result.body()?.let { response ->
                            _nowPlayingFullData.value = response
                            _nowPlayingMovieList.clear()
                            response.results?.forEach { result ->
                                _nowPlayingMovieList.add(result!!)
                            }
                        }
                        delay(3000)
                        _apiCompleteMap[0] = true
                    }
                    is NetworkResult.Failure -> {

                    }

                   /* is NetworkResult.Loading -> {

                    }*/
                }

            }
            useCases.listTopRatedMovies.invoke(Constants.LANG, "1").collect {
                when (it) {
                    is NetworkResult.Success -> {

                        it.result.body()?.let { response ->
                            _topRatedMovieList.clear()
                            response.results?.forEach { result ->
                                _topRatedMovieList.add(result!!)
                            }
                            delay(3000)
                            _apiCompleteMap[1] = true

                        }
                    }

                    is NetworkResult.Failure -> {
                    }

                    /*is NetworkResult.Loading -> {
                    }*/
                }
            }
        }
    }

    private fun initMapValues() {
        _apiCompleteMap[0] = false
        _apiCompleteMap[1] = false
    }

}