package com.example.payskytask.appFeature.domain.UTILS

sealed class Result<out T> {
    object Loading : Result<Nothing>()
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val message: String) : Result<Nothing>()
    data class ErrorWithResult<out T>(val message: String, val data: T) : Result<T>()

}