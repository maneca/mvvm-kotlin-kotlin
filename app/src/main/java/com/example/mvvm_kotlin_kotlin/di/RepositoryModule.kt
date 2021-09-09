package com.example.mvvm_kotlin_kotlin.di

import android.content.Context
import com.example.mvvm_kotlin_kotlin.api.CountriesApi
import com.example.mvvm_kotlin_kotlin.db.dao.CountriesDao
import com.example.mvvm_kotlin_kotlin.repository.CountriesRepository
import com.example.mvvm_kotlin_kotlin.repository.CountriesRepositoryImp
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {

    fun provideCountriesRepository(api: CountriesApi, context: Context, dao: CountriesDao): CountriesRepository {

        return CountriesRepositoryImp(api, context, dao)
    }

    single { provideCountriesRepository(get(), androidContext(), get()) }
}