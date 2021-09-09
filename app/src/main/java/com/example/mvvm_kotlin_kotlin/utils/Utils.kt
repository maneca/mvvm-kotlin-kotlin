package com.example.mvvm_kotlin_kotlin.utils

import retrofit2.Response
import java.lang.Error
import java.lang.Exception

object Utils {

    fun <T: Any> handleApiError(resp: Response<T>): AppResult.Error{
        val error = ApiErrorUtils.parseError(resp)
        return AppResult.Error(Exception(error.message))
    }

    fun <T: Any> handleSuccess(response: Response<T>): AppResult<T>{
        response.body()?.let {
            return AppResult.Success(it)
        } ?: return handleApiError(response)
    }
}