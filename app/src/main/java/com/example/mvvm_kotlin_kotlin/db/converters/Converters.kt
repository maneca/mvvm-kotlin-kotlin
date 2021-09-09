package com.example.mvvm_kotlin_kotlin.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun fromStringToList(value: String): List<String>{
        val listType = object: TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayListToString(list: List<String>): String{
        return Gson().toJson(list)
    }
}