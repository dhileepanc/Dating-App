package com.datingapp.app.base

sealed class NetworkResult<out H> {
    data class Success<out T>(val body: T) : NetworkResult<T>()
    data class Failure(val message: String) : NetworkResult<Nothing>()
    data class UnAuthorized(val message: String) : NetworkResult<Nothing>()
    object Pending : NetworkResult<Nothing>()
    object Complete : NetworkResult<Nothing>()
}