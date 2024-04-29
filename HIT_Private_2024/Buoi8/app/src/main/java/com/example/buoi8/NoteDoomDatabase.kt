package com.example.buoi8

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteRoomRepository : RoomDatabase() {
    
}