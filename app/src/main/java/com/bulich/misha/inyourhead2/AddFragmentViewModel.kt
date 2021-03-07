package com.bulich.misha.inyourhead2

import androidx.lifecycle.ViewModel
import com.bulich.misha.inyourhead2.model.Note
import com.bulich.misha.inyourhead2.repository.NoteRepository

class AddFragmentViewModel : ViewModel() {
    private val noteRepository = NoteRepository.get()
    fun insertNote(note: Note){
        noteRepository.insertNote(note)
    }
}