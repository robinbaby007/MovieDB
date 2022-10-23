package com.example.moviedb.domain.use_cases

import com.example.moviedb.data.retrofit.CastList
import com.example.moviedb.data.retrofit.MovieDetailsResponse
import com.example.moviedb.domain.MovieDBRepository
import retrofit2.Response
import javax.inject.Inject

class CastList @Inject constructor(private val movieDBRepository: MovieDBRepository) {
    suspend operator fun invoke(id: String): kotlinx.coroutines.flow.Flow<Response<CastList>> {
        return movieDBRepository.castList(id)
    }
}