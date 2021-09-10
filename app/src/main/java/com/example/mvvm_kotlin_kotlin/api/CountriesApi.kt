package com.example.mvvm_kotlin_kotlin.api

import com.example.mvvm_kotlin_kotlin.db.model.CountriesData
import retrofit2.Response
import retrofit2.http.GET

interface CountriesApi {

    @GET("rest/v2")
    suspend fun getAllCountries(): Response<List<CountriesData>>
}