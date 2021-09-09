package com.example.mvvm_kotlin_kotlin.db.converters

import androidx.room.TypeConverter
import com.example.mvvm_kotlin_kotlin.db.model.Native
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class NativeConverter {
    @TypeConverter
    fun stringToNative(json: String): Native? {
        val gson = Gson()
        val type = object : TypeToken<Native>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun nativeToString(name: Native): String {
        val gson = Gson()
        val type = object : TypeToken<Native>() {}.type
        return gson.toJson(name, type)
    }
}