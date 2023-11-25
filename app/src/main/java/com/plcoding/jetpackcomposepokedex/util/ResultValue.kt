package com.plcoding.jetpackcomposepokedex.util

sealed class ResultValue<out T : Any?> {
    data class Success<out T>(val data: T) : ResultValue<T>()
    data class Error(val exception: Exception) : ResultValue<Nothing>()
    class Loading<T>(val data: T? = null) : ResultValue<T>()
}
