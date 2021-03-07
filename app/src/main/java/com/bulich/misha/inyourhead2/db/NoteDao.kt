package com.bulich.misha.inyourhead2.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.bulich.misha.inyourhead2.model.Note

@Dao
interface NoteDao {

    @Query("SELECT * from my_note ORDER BY title")
    fun getNotes(): LiveData<List<Note>>

    @Query("SELECT * from my_note WHERE title=(:name)")
    fun getNote(name: String): LiveData<Note?>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertNote(note: Note)

    @Update
    fun updateNote(note: Note)

    @Delete
    fun deleteNote(note: Note)

}