package com.example.mvvm_kotlin_kotlin.db.converters

import androidx.room.TypeConverter
import com.example.mvvm_kotlin_kotlin.db.model.Language
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LanguagesConverter {

    @TypeConverter
    fun stringToLanguages(json: String): List<Language>? {
        val gson = Gson()
        val type = object : TypeToken<List<Language>>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun languagesToString(languages: List<Language>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Language>>() {}.type
        return gson.toJson(languages, type)
    }
}