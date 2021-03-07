package com.bulich.misha.inyourhead2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//Привет
class MainActivity : AppCompatActivity(), NoteListFragment.Callbacks,
    NoteListFragment.CallAddFragment, NoteDetailsFragment.CallBack1{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currentFragment = supportFragmentManager
            .findFragmentById(R.id.fragment_container)

        if (currentFragment == null){
            val fragment = NoteListFragment.newInstance()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()
        }
    }

    override fun onNoteSelected(title: String) {
        val fragment = NoteDetailsFragment.newInstance(title)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onAddNoteFragment() {
        val fragment = AddNoteFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onCallBack() {
        val fragment = NoteListFragment.newInstance()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}