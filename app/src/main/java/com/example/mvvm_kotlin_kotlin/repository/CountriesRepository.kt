package com.example.mvvm_kotlin_kotlin.repository

import com.example.mvvm_kotlin_kotlin.db.model.Country
import com.example.mvvm_kotlin_kotlin.utils.AppResult

interface CountriesRepository {

    suspend fun getAllCountries(): AppResult<List<Country>>

    suspend fun updateFavourite(countryId: Int, isFavourite: Boolean): AppResult<Boolean>

    suspend fun getFavourites(): AppResult<List<Country>>
}