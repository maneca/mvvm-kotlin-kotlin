package com.example.mvvm_kotlin_kotlin.db.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
class Currency (
    val code: String?,
    val name: String?,
    val symbol: String?
): Parcelable