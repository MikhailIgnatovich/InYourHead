package com.bulich.misha.inyourhead2.repository

import android.app.Application

class NoteApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        NoteRepository.initialize(this)
    }
}