package com.example.mvvm_kotlin_kotlin.db.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "Native")
@Parcelize
data class Native (
    @PrimaryKey(autoGenerate = true) val id: Int,
    val common: String? = "",
    val official: String? = ""
) : Parcelable