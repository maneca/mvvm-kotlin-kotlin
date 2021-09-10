package com.example.mvvm_kotlin_kotlin.db.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import org.intellij.lang.annotations.Language
import java.util.*

@Entity(tableName = "Countries")
@Parcelize
class CountriesData(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String?,
    val topLevelDomain: List<String>?,
    val currencies: List<Currencies>?,
    val callingCodes: List<String>?,
    val capital: String?,
    val region: String?,
    val subregion: String?,
    val languages: List<Languages>?,
    val translations: Translations?,
    val demonym: String?,
    val borders: List<String>?,
    val flag: String?,
    val area: Double?
) : Parcelable