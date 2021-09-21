package com.example.mvvm_kotlin_kotlin.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mvvm_kotlin_kotlin.db.converters.*
import com.example.mvvm_kotlin_kotlin.db.dao.CountriesDao
import com.example.mvvm_kotlin_kotlin.db.model.Country

@Database(
    entities = [Country::class],
    version = 1, exportSchema = false
)

@TypeConverters(Converters::class, TranslationsConverter::class, LanguagesConverter::class, CurrenciesConverter::class)
abstract class CountriesDatabase : RoomDatabase(){
    abstract val countriesDao : CountriesDao
}