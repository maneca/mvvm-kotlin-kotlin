package com.example.mvvm_kotlin_kotlin.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvvm_kotlin_kotlin.db.model.CountriesData

@Dao
interface CountriesDao {

    @Query("SELECT * FROM Countries")
    fun findAll(): List<CountriesData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(users: List<CountriesData>)
}