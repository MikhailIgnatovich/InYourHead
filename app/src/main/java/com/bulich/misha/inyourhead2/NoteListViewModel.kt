package com.bulich.misha.inyourhead2

import androidx.lifecycle.ViewModel
import com.bulich.misha.inyourhead2.repository.NoteRepository

class NoteListViewModel : ViewModel() {
    private val noteRepository = NoteRepository.get()
    val noteListLivedata = noteRepository.getNotes()
}