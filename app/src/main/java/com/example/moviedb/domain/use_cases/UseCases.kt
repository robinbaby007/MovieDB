package com.example.moviedb.domain.use_cases

data class UseCases(
    val listNowPlayingMovies: ListNowPlayingMovies,
    val listTopRatedMovies: ListTopRatedMovies,
    val movieDetails: MovieDetails,
    val castList: CastList
)