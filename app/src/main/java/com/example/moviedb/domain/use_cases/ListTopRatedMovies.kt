package com.example.moviedb.domain.use_cases

import com.example.moviedb.data.retrofit.NowPlayingMovieResponse
import com.example.moviedb.domain.MovieDBRepository
import retrofit2.Response
import javax.inject.Inject

class ListTopRatedMovies @Inject constructor(private val movieDBRepository: MovieDBRepository) {
    suspend operator fun invoke(lang: String, page: String) :kotlinx.coroutines.flow.Flow<Response<NowPlayingMovieResponse>> {
        return movieDBRepository.topRatedList(lang, page)
    }
}