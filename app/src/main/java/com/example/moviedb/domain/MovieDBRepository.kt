package com.example.moviedb.domain
import com.example.moviedb.data.retrofit.NowPlayingMovieResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface MovieDBRepository {

    suspend fun specificMovieList(lang:String,page:Int):Flow<Response<NowPlayingMovieResponse>>

}