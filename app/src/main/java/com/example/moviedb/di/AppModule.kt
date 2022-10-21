package com.example.moviedb.di

import com.example.moviedb.data.retrofit.MovieDBInterface
import com.example.moviedb.domain.MovieDBRepository
import com.example.moviedb.domain.use_cases.ListNowPlayingMovies
import com.example.moviedb.domain.use_cases.UseCases
import com.example.moviedb.utils.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun loggingInterceptor() = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    fun okHttpClient(logging: HttpLoggingInterceptor) = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    @Provides
    @Singleton
    fun retrofit(client: OkHttpClient): Retrofit = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    @Provides
    @Singleton
    fun createMovieApi(retrofit: Retrofit): MovieDBInterface =
        retrofit.create(MovieDBInterface::class.java)

    @Provides
    @Singleton
    fun useCases(movieDBRepository: MovieDBRepository): UseCases = UseCases(ListNowPlayingMovies(movieDBRepository))

}