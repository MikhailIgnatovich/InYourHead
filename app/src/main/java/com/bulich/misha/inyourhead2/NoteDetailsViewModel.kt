package com.bulich.misha.inyourhead2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.bulich.misha.inyourhead2.model.Note
import com.bulich.misha.inyourhead2.repository.NoteRepository

class NoteDetailsViewModel : ViewModel() {
    private val noteRepository = NoteRepository.get()
    private val noteTitleLiveData = MutableLiveData<String>()
    var noteLiveData: LiveData<Note> = Transformations.switchMap(noteTitleLiveData) {
        noteRepository.getNote(it)
    }

    fun getNote(title: String){
        noteTitleLiveData.value = title
    }

    fun updateNote(note: Note){
        noteRepository.updateNote(note)
    }

    fun deleteNote(note: Note){
        noteRepository.deleteNote(note)
    }
}