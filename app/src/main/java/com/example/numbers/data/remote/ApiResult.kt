package com.example.numbers.data.remote

sealed class ApiResult<T : Any> {
    data class Success<T : Any>(val data: T) : ApiResult<T>()
    data class Error<T : Any>(val code: Int, val message: String?) : ApiResult<T>()
    data class Exception<T : Any>(val e: Throwable) : ApiResult<T>()
}