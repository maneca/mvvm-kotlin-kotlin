package com.example.mvvm_kotlin_kotlin.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.mvvm_kotlin_kotlin.db.model.Country
import com.example.mvvm_kotlin_kotlin.db.model.Currency
import com.example.mvvm_kotlin_kotlin.db.model.Language
import com.example.mvvm_kotlin_kotlin.db.model.Translations
import com.example.mvvm_kotlin_kotlin.repository.CountriesRepository
import com.example.mvvm_kotlin_kotlin.repository.CountriesRepositoryImp
import com.example.mvvm_kotlin_kotlin.utils.AppResult
import com.example.mvvm_kotlin_kotlin.utils.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import java.lang.Exception

@OptIn(ExperimentalCoroutinesApi::class)
class CountriesViewModelTest{

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    @Mock
    private val mockRepo : CountriesRepository = mock(CountriesRepositoryImp::class.java)

    private lateinit var viewModel: CountriesViewModel

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
        viewModel = CountriesViewModel(mockRepo)
    }

    @Test
    fun `get all countries, throws error`() = runBlockingTest{
        assertNotNull(mockRepo)
        `when`(mockRepo.getAllCountries()).thenReturn(AppResult.Error(Exception("error retrieving data" )))

        viewModel.getAllCountries()
        assertEquals("error retrieving data", viewModel.showError.value)
    }

    @Test
    fun `get all countries, successful`() = runBlockingTest{

        assertNotNull(mockRepo)
        `when`(mockRepo.getAllCountries()).thenReturn(AppResult.Success(listOf(country)))

        viewModel.getAllCountries()
        assertEquals(1, viewModel.countriesList.value.size)
        assertEquals(country.name, viewModel.countriesList.value[0].name)
    }

    @Test
    fun `get favourites, throws error`() = runBlockingTest {
        assertNotNull(mockRepo)
        `when`(mockRepo.getFavourites()).thenReturn(AppResult.Error(Exception("Error retrieving data")))

        viewModel.getFavourites()
        assertEquals("Error retrieving data", viewModel.showError.value)
    }

    @Test
    fun `get favourites, successful`() = runBlockingTest {
        assertNotNull(mockRepo)
        country.isFavourite = true;
        `when`(mockRepo.getFavourites()).thenReturn(AppResult.Success(listOf(country)))

        viewModel.getFavourites()
        assertEquals(country.name, viewModel.countriesList.value[0].name)
        assertTrue(viewModel.countriesList.value[0].isFavourite)
    }

    @Test
    fun `update favourites, throws error`() = runBlockingTest {
        assertNotNull(mockRepo)
        `when`(mockRepo.updateFavourite(any(Int::class.java), any(Boolean::class.java)))
            .thenReturn(AppResult.Error(Exception("Error retrieving data")))

        viewModel.updateFavourite(1, true)
        assertEquals("Error retrieving data", viewModel.showError.value)
    }
}