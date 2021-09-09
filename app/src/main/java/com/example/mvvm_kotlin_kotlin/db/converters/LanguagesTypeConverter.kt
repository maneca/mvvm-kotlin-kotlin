package com.example.mvvm_kotlin_kotlin.db.converters

import androidx.room.TypeConverter
import com.example.mvvm_kotlin_kotlin.db.model.Languages
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LanguagesTypeConverter {

    @TypeConverter
    fun stringToLanguages(value: String): Languages {
        val type = object : TypeToken<Languages>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun languagesToString(value: Languages): String{
        val type = object : TypeToken<String>() {}.type
        return Gson().toJson(value, type)
    }
}