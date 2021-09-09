package com.example.mvvm_kotlin_kotlin.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mvvm_kotlin_kotlin.db.converters.*
import com.example.mvvm_kotlin_kotlin.db.dao.CountriesDao
import com.example.mvvm_kotlin_kotlin.db.model.CountriesData
import com.example.mvvm_kotlin_kotlin.db.model.Languages
import com.example.mvvm_kotlin_kotlin.db.model.Name
import com.example.mvvm_kotlin_kotlin.db.model.Translations

@Database(
    entities = [CountriesData::class, Languages::class, Name::class, Translations::class],
    version = 1, exportSchema = false
)

@TypeConverters(Converters::class, LanguagesTypeConverter::class, NameConverter::class, NativeConverter::class, TranslationsConverter::class)
abstract class CountriesDatabase : RoomDatabase(){
    abstract val countriesDao : CountriesDao
}