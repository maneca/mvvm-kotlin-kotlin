package com.example.mvvm_kotlin_kotlin.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.viewModelScope
import com.example.mvvm_kotlin_kotlin.db.model.CountriesData
import com.example.mvvm_kotlin_kotlin.repository.CountriesRepository
import com.example.mvvm_kotlin_kotlin.utils.AppResult
import com.example.mvvm_kotlin_kotlin.utils.SingleLiveEvent
import kotlinx.coroutines.launch

class CountriesViewModel(private val repository: CountriesRepository) : ViewModel() {

    val showLoading = ObservableBoolean()
    val countriesList : MutableState<List<CountriesData>> = mutableStateOf(ArrayList())
    var backupCountriesList : List<CountriesData> = listOf()
    val showError = SingleLiveEvent<String?>()

    init {
        getAllCountries()
    }

    fun getAllCountries() {

        showLoading.set(true)

        if(backupCountriesList.isNotEmpty()){
            showLoading.set(false)
            countriesList.value = backupCountriesList
            showError.value = null
        }else{
            viewModelScope.launch {
                val result = repository.getAllCountries()

                showLoading.set(false)
                when(result){
                    is AppResult.Success -> {
                        countriesList.value = result.successData
                        backupCountriesList = result.successData
                        showError.value = null
                    }
                    is AppResult.Error -> showError.value = result.exception.message
                }
            }
        }
    }

    fun updateFavourite(countryId: Int, isFavourite: Boolean){

        viewModelScope.launch {
            try {
                repository.updateFavourite(countryId, isFavourite)
            } catch (e: Exception) {
                showError.value = e.message
            }
        }
    }

    fun getFavourites(){
        viewModelScope.launch {
            when(val favourites = repository.getFavourites()){
                is AppResult.Success ->
                    countriesList.value = favourites.successData
            }

        }
    }
}