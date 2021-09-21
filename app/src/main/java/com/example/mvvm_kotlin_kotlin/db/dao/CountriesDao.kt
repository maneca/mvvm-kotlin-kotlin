package com.example.mvvm_kotlin_kotlin.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvvm_kotlin_kotlin.db.model.Country

@Dao
interface CountriesDao {

    @Query("SELECT * FROM Countries")
    fun getAllCountries(): List<Country>

    @Query("UPDATE Countries SET isFavourite = :isFavourite WHERE id = :countryId")
    fun updateFavourite(countryId: Int, isFavourite: Boolean)

    @Query("SELECT * FROM COUNTRIES WHERE isFavourite = :isFavourite")
    fun getFavourites(isFavourite: Boolean): List<Country>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCountries(users: List<Country>)
}