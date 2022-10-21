package com.example.moviedb.domain.use_cases

import com.example.moviedb.data.retrofit.NowPlayingMovieResponse
import com.example.moviedb.domain.MovieDBRepository
import retrofit2.Response
import javax.inject.Inject

class ListNowPlayingMovies @Inject constructor(private val movieDBRepository: MovieDBRepository) {
    suspend operator fun invoke(lang: String, page: Int) :kotlinx.coroutines.flow.Flow<Response<NowPlayingMovieResponse>> {
        return movieDBRepository.specificMovieList(lang, page)
    }

}