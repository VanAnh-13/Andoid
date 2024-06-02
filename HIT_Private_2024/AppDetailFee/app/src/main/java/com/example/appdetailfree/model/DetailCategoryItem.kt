package com.example.appdetailfree.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailCategoryItem(
    val label: String,
    val date: String,
    val cost: String
) : Parcelable