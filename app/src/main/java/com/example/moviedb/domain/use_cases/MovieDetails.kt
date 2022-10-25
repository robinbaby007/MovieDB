package com.example.moviedb.domain.use_cases

import com.example.moviedb.data.retrofit.MovieDetailsResponse
import com.example.moviedb.data.retrofit.NowPlayingMovieResponse
import com.example.moviedb.domain.MovieDBRepository
import com.example.moviedb.utils.NetworkResult
import retrofit2.Response
import javax.inject.Inject

class MovieDetails @Inject constructor(private val movieDBRepository: MovieDBRepository) {
    suspend operator fun invoke(id: String) :kotlinx.coroutines.flow.Flow<NetworkResult<Response<MovieDetailsResponse>>> {
        return movieDBRepository.movieDetails(id)
    }
}