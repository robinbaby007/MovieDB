package com.example.moviedb.domain
import com.example.moviedb.data.retrofit.CastList
import com.example.moviedb.data.retrofit.MovieDetailsResponse
import com.example.moviedb.data.retrofit.NowPlayingMovieResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface MovieDBRepository {

    suspend fun nowPlayingList(lang:String,page:String):Flow<Response<NowPlayingMovieResponse>>
    suspend fun topRatedList(lang:String,page:String):Flow<Response<NowPlayingMovieResponse>>
    suspend fun movieDetails(movieId:String):Flow<Response<MovieDetailsResponse>>
    suspend fun castList(movieId:String):Flow<Response<CastList>>

}