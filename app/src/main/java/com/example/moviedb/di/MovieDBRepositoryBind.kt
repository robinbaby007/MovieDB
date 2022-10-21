package com.example.moviedb.di

import com.example.moviedb.data.repository.MovieDBRepositoryImpl
import com.example.moviedb.domain.MovieDBRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class MovieDBRepositoryBind {

    @Binds
    @Singleton
    abstract fun bindRepository(movieDBRepositoryImpl: MovieDBRepositoryImpl): MovieDBRepository

}