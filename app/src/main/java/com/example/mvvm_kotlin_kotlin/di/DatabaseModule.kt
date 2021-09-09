package com.example.mvvm_kotlin_kotlin.di

import android.app.Application
import androidx.room.Room
import com.example.mvvm_kotlin_kotlin.db.CountriesDatabase
import com.example.mvvm_kotlin_kotlin.db.dao.CountriesDao
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {

    fun provideDatabase(application: Application): CountriesDatabase {

        return Room.databaseBuilder(application, CountriesDatabase::class.java, "countries")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideCountriesDao(database: CountriesDatabase): CountriesDao {

        return database.countriesDao
    }

    single { provideDatabase(androidApplication()) }
    single { provideCountriesDao(get()) }
}