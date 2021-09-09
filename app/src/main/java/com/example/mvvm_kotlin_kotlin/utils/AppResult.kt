package com.example.mvvm_kotlin_kotlin.utils

import java.lang.Exception

sealed class AppResult<out T> {

    data class Success<out T>(val successData: T): AppResult<T>()

    class Error(val exception: Exception, val message: String? = exception.localizedMessage) : AppResult<Nothing>()
}