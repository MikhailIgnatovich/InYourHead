package com.bulich.misha.inyourhead2.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bulich.misha.inyourhead2.model.Note

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao
}