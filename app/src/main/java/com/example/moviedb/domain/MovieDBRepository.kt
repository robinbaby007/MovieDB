package com.example.moviedb.domain
import com.example.moviedb.data.retrofit.CastList
import com.example.moviedb.data.retrofit.MovieDetailsResponse
import com.example.moviedb.data.retrofit.NowPlayingMovieResponse
import com.example.moviedb.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface MovieDBRepository {

    suspend fun nowPlayingList(lang:String,page:String):Flow<NetworkResult<Response<NowPlayingMovieResponse>>>
    suspend fun topRatedList(lang:String,page:String): Flow<NetworkResult<Response<NowPlayingMovieResponse>>>
    suspend fun movieDetails(movieId:String):Flow<Response<MovieDetailsResponse>>
    suspend fun castList(movieId:String):Flow<Response<CastList>>

}