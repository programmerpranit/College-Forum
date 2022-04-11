package com.psp.collegeforum.util

sealed class Resource<T>(val data: T?, val message: String?, val status: Int) {
    class Success<T>(data: T, status: Int) : Resource<T>(data, null, status)
    class Error<T>(message: String, status: Int) : Resource<T>(null, message, status)
}
