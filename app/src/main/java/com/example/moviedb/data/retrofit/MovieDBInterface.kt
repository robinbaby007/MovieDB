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
// https://api.themoviedb.org/3/movie/now_playing?api_key=b11da8d22d4a162a55fe5e732a251133&language=en-US&page=1

}