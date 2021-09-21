package com.example.mvvm_kotlin_kotlin.db.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.mvvm_kotlin_kotlin.db.CountriesDatabase
import com.example.mvvm_kotlin_kotlin.db.model.Country
import com.example.mvvm_kotlin_kotlin.db.model.Currency
import com.example.mvvm_kotlin_kotlin.db.model.Language
import com.example.mvvm_kotlin_kotlin.db.model.Translations
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class CountriesDaoTest {

    private lateinit var database : CountriesDatabase
    private lateinit var dao : CountriesDao

    @Before
    fun setup(){
        database = Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(), CountriesDatabase::class.java)
                       .allowMainThreadQueries()
                       .build()

        dao = database.countriesDao
    }

    @After
    fun teardown(){
        database.close()
    }

    @Test
    fun insertCountries() {
        val country = Country(
            1,
            "Afghanistan",
            listOf(".af"),
            listOf(Currency("AFN", "Afghan afghani", "؋")),
            listOf("93"),
            "Kabul",
            "Asia",
            "Southern Asia",
            listOf(Language("ps", "pus", "Pashto", "")),
            Translations("Afghanistan", "", "", "", ""),
            "Afghan",
            listOf("IRN", "PAK", "TKM"),
            "",
            652230.0,
            false)
        dao.addCountries(listOf(country))

        val allCountries = dao.getAllCountries()
        Assert.assertEquals(allCountries.size, 1)
        Assert.assertTrue(allCountries.any { it.name == "Afghanistan" })
    }

    @Test
    fun updateFavourite(){
        val country = Country(
            1,
            "Afghanistan",
            listOf(".af"),
            listOf(Currency("AFN", "Afghan afghani", "؋")),
            listOf("93"),
            "Kabul",
            "Asia",
            "Southern Asia",
            listOf(Language("ps", "pus", "Pashto", "")),
            Translations("Afghanistan", "", "", "", ""),
            "Afghan",
            listOf("IRN", "PAK", "TKM"),
            "",
            652230.0,
            false)
        dao.addCountries(listOf(country))

        var allCountries = dao.getAllCountries()
        Assert.assertTrue(allCountries.any { it.name == "Afghanistan" && !it.isFavourite})

        dao.updateFavourite(1, true)

        allCountries = dao.getAllCountries()
        Assert.assertTrue(allCountries.any { it.name == "Afghanistan" && it.isFavourite})
    }

    @Test
    fun getFavourites(){
        val country = Country(
            1,
            "Afghanistan",
            listOf(".af"),
            listOf(Currency("AFN", "Afghan afghani", "؋")),
            listOf("93"),
            "Kabul",
            "Asia",
            "Southern Asia",
            listOf(Language("ps", "pus", "Pashto", "")),
            Translations("Afghanistan", "", "", "", ""),
            "Afghan",
            listOf("IRN", "PAK", "TKM"),
            "",
            652230.0,
            false)
        dao.addCountries(listOf(country))

        var allFavouriteCountries = dao.getFavourites(true)
        Assert.assertEquals(allFavouriteCountries.size, 0)

        dao.updateFavourite(1, true)

        allFavouriteCountries = dao.getFavourites(true)
        Assert.assertEquals(allFavouriteCountries.size, 1)
    }
}