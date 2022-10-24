package com.example.moviedb.utils

/*sealed class NetworkResult<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : NetworkResult<T>(data)
    class Error<T>(message: String?, data: T? = null) : NetworkResult<T>(data, message)
    class Loading<T>() : NetworkResult<T>()
}*/




sealed class NetworkResult<out R> {
    data class Success<out R>(val result: R) : NetworkResult<R>()
    data class Failure(val message: String?) : NetworkResult<Nothing>()
    object Loading : NetworkResult<Nothing>()
}
