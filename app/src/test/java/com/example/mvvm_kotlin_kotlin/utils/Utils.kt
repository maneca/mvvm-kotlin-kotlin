package com.example.mvvm_kotlin_kotlin.utils

import com.example.mvvm_kotlin_kotlin.db.model.Country
import com.example.mvvm_kotlin_kotlin.db.model.Currency
import com.example.mvvm_kotlin_kotlin.db.model.Language
import com.example.mvvm_kotlin_kotlin.db.model.Translations

object Utils {
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
}
