package com.example.mvvm_kotlin_kotlin.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mvvm_kotlin_kotlin.db.converters.*
import com.example.mvvm_kotlin_kotlin.db.dao.CountriesDao
import com.example.mvvm_kotlin_kotlin.db.model.CountriesData
import com.example.mvvm_kotlin_kotlin.db.model.Currencies
import com.example.mvvm_kotlin_kotlin.db.model.Languages
import com.example.mvvm_kotlin_kotlin.db.model.Translations

@Database(
    entities = [CountriesData::class, Translations::class, Languages::class, Currencies::class],
    version = 2, exportSchema = false
)

@TypeConverters(Converters::class, TranslationsConverter::class, LanguagesConverter::class, CurrenciesConverter::class)
abstract class CountriesDatabase : RoomDatabase(){
    abstract val countriesDao : CountriesDao
}