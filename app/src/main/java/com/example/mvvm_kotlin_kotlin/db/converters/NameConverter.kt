package com.example.mvvm_kotlin_kotlin.db.converters

import androidx.room.TypeConverter
import com.example.mvvm_kotlin_kotlin.db.model.Name
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class NameConverter {

    @TypeConverter
    fun nameToString(value: Name): String{
        val type = object : TypeToken<Name>() {}.type
        return Gson().toJson(value, type)
    }

    @TypeConverter
    fun stringToName(value: String): Name {
        val type = object : TypeToken<Name>() {}.type
        return Gson().fromJson(value, type)
    }
}