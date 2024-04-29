package com.example.test.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Entity(tableName = "note_table")
data class Note(
    @PrimaryKey(autoGenerate = true)
    val key: Int? = null,

    @ColumnInfo(name = "title")
    val title: String?,

    @ColumnInfo(name = "content")
    val content: String?,

    @ColumnInfo(name = "create")
    val createAt: LocalDateTime = LocalDateTime.now(),

    @ColumnInfo(name = "is_favorite")
    var isFavorite: Boolean
) : Parcelable {
    val createdDateFormatted: String
        get() = createAt.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"))
}