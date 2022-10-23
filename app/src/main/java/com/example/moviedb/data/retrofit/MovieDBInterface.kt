package com.example.moviedb.data.retrofit

import com.example.moviedb.utils.Constants
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDBInterface {
    @GET("movie/now_playing")
    suspend fun nowPlaying(
        @Query("api_key")
        apiKey: String = Constants.API_KEY,
        @Query("language")
        language: String = Constants.LANG,
        @Query("page")
        page: String = "1",
    ):Response<NowPlayingMovieResponse>

    @GET("movie/top_rated")
    suspend fun topRated(
        @Query("api_key")
        apiKey: String = Constants.API_KEY,
        @Query("language")
        language: String = Constants.LANG,
        @Query("page")
        page: String = "1",
    ):Response<NowPlayingMovieResponse>


}