package com.example.mvvm_kotlin_kotlin.di

import com.example.mvvm_kotlin_kotlin.viewmodel.CountriesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        CountriesViewModel(repository = get())
    }
}