package com.example.mvvm_kotlin_kotlin.db.converters

import androidx.room.TypeConverter
import com.example.mvvm_kotlin_kotlin.db.model.Currency
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CurrenciesConverter {

    @TypeConverter
    fun stringToCurrencies(json: String): List<Currency>? {
        val gson = Gson()
        val type = object : TypeToken<List<Currency>>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun currenciesToString(languages: List<Currency>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Currency>>() {}.type
        return gson.toJson(languages, type)
    }
}