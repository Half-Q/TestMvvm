package com.half.test.util

sealed class NetworkStatus<out T>{
    data class Success<out T>(val data: T): NetworkStatus<T>()
    data class Error(val message: String): NetworkStatus<Nothing>()
}