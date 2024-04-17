package com.example.test

import java.util.Date

data class Note(
    val title: String,
    val content: String,
    val createAt: Date,
    val isFavorite: Boolean
)
