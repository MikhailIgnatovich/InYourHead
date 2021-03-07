package com.bulich.misha.inyourhead2

import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bulich.misha.inyourhead2.model.Note

//Фрагмент добавления новой заметки
class AddNoteFragment : Fragment() {

    private lateinit var titleEditText: EditText
    private lateinit var descriptionEditText: EditText
    private lateinit var addButton: Button

    private val addFragmentViewModel: AddFragmentViewModel by lazy {
        ViewModelProvider(this).get(AddFragmentViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_note, container, false)

        titleEditText = view.findViewById(R.id.title_editText) as EditText
        descriptionEditText = view.findViewById(R.id.description_editTest) as EditText
        addButton = view.findViewById(R.id.add_note_button) as Button

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addButton.setOnClickListener {
            addNoteToDB()
        }
    }

    private fun addNoteToDB(){
        val title = titleEditText.text.toString()
        val description = descriptionEditText.text.toString()
        val note = Note(title, description)
        addFragmentViewModel.insertNote(note)
    }
    companion object {

        fun newInstance(): AddNoteFragment {
            return AddNoteFragment()
        }
    }
}