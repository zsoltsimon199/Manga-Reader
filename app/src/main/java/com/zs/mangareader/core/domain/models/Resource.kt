package com.zs.mangareader.core.domain.models

sealed class Resource<T>(data: T? = null, errorMessage: String? = null) {

    data class Success<T>(var data: T?) :
        Resource<T>(data = data)

    data class Error<T>(var data: T? = null, var message: String) :
        Resource<T>(data = data, errorMessage = message)

}