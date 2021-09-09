package com.example.mvvm_kotlin_kotlin.viewmodel

import androidx.lifecycle.ViewModel
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mvvm_kotlin_kotlin.db.model.CountriesData
import com.example.mvvm_kotlin_kotlin.repository.CountriesRepository
import com.example.mvvm_kotlin_kotlin.utils.AppResult
import com.example.mvvm_kotlin_kotlin.utils.SingleLiveEvent
import kotlinx.coroutines.launch

class CountriesViewModel(private val repository: CountriesRepository) : ViewModel() {

    val showLoading = ObservableBoolean()
    val countriesList = MutableLiveData<List<CountriesData>>()
    val showError = SingleLiveEvent<String?>()

    fun getAllCountries() {

        showLoading.set(true)

        viewModelScope.launch {
            val result = repository.getAllCountries()

            showLoading.set(false)
            when(result){
                is AppResult.Success -> {
                    countriesList.value = result.successData!!
                    showError.value = null
                }
                is AppResult.Error -> showError.value = result.exception.message
            }
        }
    }
}