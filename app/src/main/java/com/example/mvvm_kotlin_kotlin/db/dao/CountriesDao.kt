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

    @Query("UPDATE Countries SET isFavourite = :isFavourite WHERE id = :countryId")
    fun updateFavourite(countryId: Int, isFavourite: Boolean)

    @Query("SELECT * FROM COUNTRIES WHERE isFavourite = :isFavourite")
    fun getFavourites(isFavourite: Boolean): List<CountriesData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(users: List<CountriesData>)
}