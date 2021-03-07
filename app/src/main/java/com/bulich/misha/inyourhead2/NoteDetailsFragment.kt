package com.bulich.misha.inyourhead2

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bulich.misha.inyourhead2.model.Note

private const val ARG_NOTE_TITLE = "title"
class NoteDetailsFragment : Fragment() {

    interface CallBack1{
        fun onCallBack()
    }
    private var callBack: CallBack1? = null
    private lateinit var titleDetailEditText: EditText
    private lateinit var descriptionDetailsEditText: EditText
    private lateinit var changeButton: Button
    private lateinit var deleteButton: Button

    private val noteDetailsViewModel: NoteDetailsViewModel by lazy {
        ViewModelProvider(this).get(NoteDetailsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val title = arguments?.getString(ARG_NOTE_TITLE)
        title?.let { noteDetailsViewModel.getNote(it) }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callBack = context as CallBack1
    }

    override fun onDetach() {
        super.onDetach()
        callBack = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_note_details, container, false)

        titleDetailEditText = view.findViewById(R.id.title_details_editText) as EditText
        descriptionDetailsEditText = view.findViewById(R.id.description_details_editTest) as EditText
        changeButton = view.findViewById(R.id.change_note_button) as Button
        deleteButton = view.findViewById(R.id.delete_note_button) as Button

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteDetailsViewModel.noteLiveData.observe(viewLifecycleOwner, Observer {
            updateUI(it)
        })
        changeButton.setOnClickListener {
            val title = titleDetailEditText.text.toString()
            val description =  descriptionDetailsEditText.text.toString()
            if (title.isNotEmpty() && description.isNotEmpty()){
                val note = Note(title, description)
                noteDetailsViewModel.updateNote(note)
            }
        }
        deleteButton.setOnClickListener {
            val note = noteDetailsViewModel.noteLiveData.value
            note?.let {  noteDetailsViewModel.deleteNote(it)
            callBack?.onCallBack()}
        }

    }

    private fun updateUI(note: Note){
        titleDetailEditText.setText(note.title)
        descriptionDetailsEditText.setText(note.description)
    }

    companion object {
        fun newInstance(title: String): NoteDetailsFragment{
            val args = Bundle().apply {
                putString(ARG_NOTE_TITLE, title)
            }
            return NoteDetailsFragment().apply {
                arguments = args
            }
        }

    }
}