package com.example.mvvm_kotlin_kotlin.db.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "Translations")
@Parcelize
data class Translations(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val de: String?,
    val es: String?,
    val fr: String?,
    val ja: String?,
    val it: String?
) : Parcelable