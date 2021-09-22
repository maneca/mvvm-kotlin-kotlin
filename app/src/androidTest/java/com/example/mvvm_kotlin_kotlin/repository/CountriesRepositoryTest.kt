package com.example.mvvm_kotlin_kotlin.repository

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.mvvm_kotlin_kotlin.api.CountriesApi
import com.example.mvvm_kotlin_kotlin.db.dao.CountriesDao
import com.example.mvvm_kotlin_kotlin.db.model.Country
import com.example.mvvm_kotlin_kotlin.db.model.Currency
import com.example.mvvm_kotlin_kotlin.db.model.Language
import com.example.mvvm_kotlin_kotlin.db.model.Translations
import com.example.mvvm_kotlin_kotlin.utils.AppResult
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.any
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

@RunWith(AndroidJUnit4::class)
@SmallTest
class CountriesRepositoryTest {

    @Mock
    private val mockApi = Mockito.mock(CountriesApi::class.java)

    @Mock
    private val mockDao = Mockito.mock(CountriesDao::class.java)

    private lateinit var repository: CountriesRepository

    private val country = Country(
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

    @Before
    fun setup(){
        MockitoAnnotations.openMocks(this)
        repository = CountriesRepositoryImp(mockApi, ApplicationProvider.getApplicationContext(), mockDao)
    }

    @Test
    fun getAllCountriesFromNetwork() = runBlocking{
        Assert.assertNotNull(mockDao)
        Assert.assertNotNull(mockApi)
        Mockito.`when`(mockDao.getAllCountries()).thenReturn(listOf())
        Mockito.`when`(mockApi.getAllCountries()).thenReturn(Response.success(listOf(country)))

        val result = repository.getAllCountries() as AppResult.Success<List<Country>>

        Assert.assertEquals(1, result.successData.size)
        Assert.assertEquals(country.name, result.successData[0].name)
    }

    @Test
    fun getAllCountriesFromDatabase() = runBlocking {
        Assert.assertNotNull(mockDao)
        Mockito.`when`(mockDao.getAllCountries()).thenReturn(listOf(country))

        val result = repository.getAllCountries() as AppResult.Success<List<Country>>

        Assert.assertEquals(1, result.successData.size)
        Assert.assertEquals(country.name, result.successData[0].name)
    }

    @Test
    fun updateFavourite() = runBlocking{
        Assert.assertNotNull(mockDao)
        Mockito.`when`(mockDao.updateFavourite(any(Int::class.java), any(Boolean::class.java)))

        val result = repository.updateFavourite(1, true) as AppResult.Success<Boolean>

        Assert.assertTrue(result.successData)
    }

    @Test
    fun getFavourites() = runBlocking {
        Assert.assertNotNull(mockDao)
        country.isFavourite = true
        Mockito.`when`(mockDao.getFavourites(any(Boolean::class.java))).thenReturn(listOf(country))

        val result = repository.getFavourites() as AppResult.Success<List<Country>>

        Assert.assertEquals(1, result.successData.size)
        Assert.assertEquals(country.name, result.successData[0].name)
        Assert.assertTrue(result.successData[0].isFavourite)
    }
}