package com.example.moviedb.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun test(): String {
        return "Robin"
    }

    @Provides
    @Singleton
    fun test2(value: String): Int {
        return 1
    }


}