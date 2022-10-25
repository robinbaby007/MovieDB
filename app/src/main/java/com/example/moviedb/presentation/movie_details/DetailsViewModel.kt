package com.example.moviedb.presentation.movie_details

import android.util.Log
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.*
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviedb.data.retrofit.CastList
import com.example.moviedb.data.retrofit.MovieDetailsResponse
import com.example.moviedb.domain.use_cases.UseCases
import com.example.moviedb.utils.NetworkResult
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    useCases: UseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {


    private val _movieDetails: MutableState<MovieDetailsResponse> =
        mutableStateOf(MovieDetailsResponse())
    val movieDetails: State<MovieDetailsResponse> = _movieDetails
    private var _castList = mutableStateListOf<CastList.Cast>()
    val castList: List<CastList.Cast> = _castList
    private var _apiCompleteMap = mutableStateMapOf<Int, Boolean>()
    val apiCompleteMap: Map<Int, Boolean> = _apiCompleteMap

    init {
        initMapValues()
        savedStateHandle.get<String>("movieId")?.let { passwordId ->

            if (passwordId.isNotEmpty()) {

                viewModelScope.launch {

                    useCases.movieDetails.invoke(passwordId).collect { movieResponse ->

                        when (movieResponse) {

                            is NetworkResult.Success -> {
                                movieResponse.result.body()?.let {
                                    _movieDetails.value = it
                                }
                                delay(3000)
                                _apiCompleteMap[0] = true
                            }

                            is NetworkResult.Failure -> {

                            }

                            else -> {

                            }

                        }


                    }

                    useCases.castList.invoke(passwordId).collect() {
                        if (passwordId.isNotEmpty()) {
                            when (it) {
                                is NetworkResult.Success -> {

                                    it.result.body()?.let { castList ->
                                        _castList.clear()
                                        castList.cast?.forEach { cast ->
                                            _castList.add(cast!!)
                                        }
                                    }
                                    delay(3000)
                                    _apiCompleteMap[1] = true
                                }

                                is NetworkResult.Failure -> {

                                }

                                else -> {

                                }
                            }

                        }
                    }
                }


            }

        }


    }
    private fun initMapValues() {
        _apiCompleteMap[0] = false
        _apiCompleteMap[1] = false
    }
}
