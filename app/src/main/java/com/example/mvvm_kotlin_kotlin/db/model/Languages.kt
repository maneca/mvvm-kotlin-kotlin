package com.example.mvvm_kotlin_kotlin.db.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "Languages")
@Parcelize
class Languages(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val iso639_1: String?,
    val iso639_2: String?,
    val name: String?,
    val nativeName: String?
) : Parcelable