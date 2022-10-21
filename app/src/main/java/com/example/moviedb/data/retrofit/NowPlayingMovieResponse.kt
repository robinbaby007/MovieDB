package com.example.moviedb.data.retrofit


import com.google.gson.annotations.SerializedName

data class NowPlayingMovieResponse(
    @SerializedName("dates")
    val dates: Dates?=null,
    @SerializedName("page")
    val page: Int?=null,
    @SerializedName("results")
    val results: List<Result?>?=null,
    @SerializedName("total_pages")
    val totalPages: Int?=null,
    @SerializedName("total_results")
    val totalResults: Int?=null
) {
    data class Dates(
        @SerializedName("maximum")
        val maximum: String?,
        @SerializedName("minimum")
        val minimum: String?
    )

    data class Result(
        @SerializedName("adult")
        val adult: Boolean?,
        @SerializedName("backdrop_path")
        val backdropPath: String?,
        @SerializedName("genre_ids")
        val genreIds: List<Int?>?,
        @SerializedName("id")
        val id: Int?,
        @SerializedName("original_language")
        val originalLanguage: String?,
        @SerializedName("original_title")
        val originalTitle: String?,
        @SerializedName("overview")
        val overview: String?,
        @SerializedName("popularity")
        val popularity: Double?,
        @SerializedName("poster_path")
        val posterPath: String?,
        @SerializedName("release_date")
        val releaseDate: String?,
        @SerializedName("title")
        val title: String?,
        @SerializedName("video")
        val video: Boolean?,
        @SerializedName("vote_average")
        val voteAverage: Double?,
        @SerializedName("vote_count")
        val voteCount: Int?
    )
}