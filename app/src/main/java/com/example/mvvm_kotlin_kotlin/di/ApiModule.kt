package com.example.mvvm_kotlin_kotlin.di

import com.example.mvvm_kotlin_kotlin.api.CountriesApi
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    fun provideCountriesApi(retrofit: Retrofit): CountriesApi {
        return retrofit.create(CountriesApi::class.java)
    }

    single { provideCountriesApi(get()) }
}