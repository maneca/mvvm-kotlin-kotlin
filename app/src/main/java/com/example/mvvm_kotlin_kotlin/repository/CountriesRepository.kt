package com.example.mvvm_kotlin_kotlin.repository

import com.example.mvvm_kotlin_kotlin.db.model.CountriesData
import com.example.mvvm_kotlin_kotlin.utils.AppResult

interface CountriesRepository {

    suspend fun getAllCountries(): AppResult<List<CountriesData>>

    suspend fun updateFavourite(countryId: Int, isFavourite: Boolean)

    suspend fun getFavourites(): AppResult<List<CountriesData>>
}