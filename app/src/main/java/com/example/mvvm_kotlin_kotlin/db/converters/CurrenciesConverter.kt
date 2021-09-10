package com.example.mvvm_kotlin_kotlin.db.converters

import androidx.room.TypeConverter
import com.example.mvvm_kotlin_kotlin.db.model.Currencies
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CurrenciesConverter {

    @TypeConverter
    fun stringToCurrencies(json: String): List<Currencies>? {
        val gson = Gson()
        val type = object : TypeToken<List<Currencies>>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun currenciesToString(languages: List<Currencies>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Currencies>>() {}.type
        return gson.toJson(languages, type)
    }
}