package com.example.moviedb.data.repository

import com.example.moviedb.data.retrofit.CastList
import com.example.moviedb.data.retrofit.MovieDBInterface
import com.example.moviedb.data.retrofit.MovieDetailsResponse
import com.example.moviedb.data.retrofit.NowPlayingMovieResponse
import com.example.moviedb.domain.MovieDBRepository
import com.example.moviedb.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class MovieDBRepositoryImpl @Inject constructor(private val movieDBInterface: MovieDBInterface) :
    MovieDBRepository {

    /*@Inject private lateinit var movieDBInterface: MovieDBInterface*/

    /*override suspend fun nowPlayingList(
        lang: String,
        page: String
    ): Flow<Response<NowPlayingMovieResponse>> {
        return flow {
            emit(movieDBInterface.nowPlaying(language = lang, page = page))
        }.flowOn(Dispatchers.IO)

    }*/


    override suspend fun nowPlayingList(
        lang: String,
        page: String
    ): Flow<NetworkResult<Response<NowPlayingMovieResponse>>> {
        return flow {
//          emit(movieDBInterface.nowPlaying(language = lang, page = page))
            emit(NetworkResult.Loading)
            try {
                val response= movieDBInterface.nowPlaying(language = lang, page = page)
                emit(if (response.isSuccessful) NetworkResult.Success(response) else NetworkResult.Failure(response.code().toString()))
            }
            catch (e:Exception){
               emit(NetworkResult.Failure(e.printStackTrace().toString()))
            }
        }.flowOn(Dispatchers.IO)

    }

    override suspend fun topRatedList(
        lang: String,
        page: String
    ): Flow<NetworkResult<Response<NowPlayingMovieResponse>>> {
        return flow {
            emit(NetworkResult.Loading)
            try {
                val response = movieDBInterface.topRated(language = lang, page = page)
                emit(if (response.isSuccessful) NetworkResult.Success(response) else NetworkResult.Failure(response.code().toString()))
            } catch (e: Exception) {
                emit(NetworkResult.Failure(e.printStackTrace().toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun movieDetails(movieId: String): Flow<Response<MovieDetailsResponse>> {
        return flow {
            emit(movieDBInterface.movieDetails(movieId = movieId))
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun castList(movieId: String): Flow<Response<CastList>> {
        return flow {
            emit(movieDBInterface.castList(movieId = movieId))
        }.flowOn(Dispatchers.IO)
    }

}