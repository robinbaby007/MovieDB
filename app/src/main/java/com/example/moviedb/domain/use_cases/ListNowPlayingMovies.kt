package com.example.moviedb.domain.use_cases

import com.example.moviedb.data.retrofit.NowPlayingMovieResponse
import com.example.moviedb.domain.MovieDBRepository
import com.example.moviedb.utils.NetworkResult
import retrofit2.Response
import javax.inject.Inject

class ListNowPlayingMovies @Inject constructor(private val movieDBRepository: MovieDBRepository) {
    suspend operator fun invoke(lang: String, page: String) :kotlinx.coroutines.flow.Flow<NetworkResult<Response<NowPlayingMovieResponse>>> {
        return movieDBRepository.nowPlayingList(lang, page)
    }

}