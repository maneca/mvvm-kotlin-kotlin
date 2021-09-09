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
    val name: @RawValue Name?,
    val tld: List<String>?,
    val currency: List<String>?,
    val callingCode: List<String>?,
    val capital: String?,
    val region: String?,
    val subregion: String?,
    val nativeLanguage: @RawValue Languages?,
    val translations: Translations?,
    val denonym: String?,
    val borders: List<String>?,
    val area: Double?
) : Parcelable