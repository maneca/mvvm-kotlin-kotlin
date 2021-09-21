package com.example.mvvm_kotlin_kotlin.db.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "Countries")
@Parcelize
class Country(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String?,
    val topLevelDomain: List<String>?,
    val currencies: List<Currency>?,
    val callingCodes: List<String>?,
    val capital: String?,
    val region: String?,
    val subregion: String?,
    val languages: List<com.example.mvvm_kotlin_kotlin.db.model.Language>?,
    val translations: Translations?,
    val demonym: String?,
    val borders: List<String>?,
    val flag: String?,
    val area: Double?,
    var isFavourite: Boolean = false
) : Parcelable