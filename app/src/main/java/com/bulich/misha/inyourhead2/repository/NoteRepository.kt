package com.bulich.misha.inyourhead2.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bulich.misha.inyourhead2.db.NoteDatabase
import com.bulich.misha.inyourhead2.model.Note
import java.lang.IllegalStateException
import java.util.concurrent.Executors

private const val DATABASE_NAME = "note-database"

class NoteRepository private constructor(context: Context){

    private val database: NoteDatabase = Room.databaseBuilder(
        context.applicationContext,
        NoteDatabase::class.java,
        DATABASE_NAME
    ).build()

    private val executor = Executors.newSingleThreadExecutor()
    private val noteDao = database.noteDao()

    fun getNotes(): LiveData<List<Note>>{
        return noteDao.getNotes()
    }

    fun insertNote(note: Note){
        executor.execute {
            noteDao.insertNote(note)
        }
    }

    fun getNote(title: String): LiveData<Note?> {
        return noteDao.getNote(title)
    }

    fun updateNote(note: Note){
        executor.execute {
            noteDao.updateNote(note)
        }
    }

    fun deleteNote(note: Note){
        executor.execute {
            noteDao.deleteNote(note)
        }
    }

    companion object{
        private var INSTANCE: NoteRepository? = null
        fun initialize(context: Context){
            if (INSTANCE == null){
                INSTANCE = NoteRepository(context)
            }
        }

        fun get(): NoteRepository {
            return INSTANCE ?:
            throw IllegalStateException("NoteRepository must be initialize")
        }
    }
}