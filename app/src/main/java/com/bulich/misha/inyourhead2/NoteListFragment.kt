package com.bulich.misha.inyourhead2

import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bulich.misha.inyourhead2.model.Note

class NoteListFragment : Fragment() {

    interface Callbacks{
        fun onNoteSelected(title: String)
    }

    interface CallAddFragment{
        fun onAddNoteFragment()
    }
    private var callAddFragment: CallAddFragment? = null
    private var callbacks: Callbacks? = null
    private lateinit var recyclerView: RecyclerView
    private  var adapterNote: NoteAdapter = NoteAdapter(emptyList())
    private val noteListViewModel: NoteListViewModel by lazy {
        ViewModelProvider(this).get(NoteListViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_note_list, container, false)

        recyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteListViewModel.noteListLivedata.observe(viewLifecycleOwner, Observer {
            updateUI(it)
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
        callAddFragment = context as CallAddFragment?
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
        callAddFragment = null
    }

    companion object {
        fun newInstance(): NoteListFragment{
            return NoteListFragment()
        }
    }

    private fun updateUI(noteList: List<Note>){
        adapterNote = NoteAdapter(noteList)
        recyclerView.adapter = adapterNote
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.add_note_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.add_note -> {
                callAddFragment?.onAddNoteFragment()
                true}
            else -> return super.onOptionsItemSelected(item)
        }

    }

    private inner class NoteHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener{
        private lateinit var note: Note
        private val titleTextView: TextView = itemView.findViewById(R.id.title_note_textView)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(note: Note){
            this.note = note
            titleTextView.text = this.note.title
        }

        override fun onClick(p0: View?) {
            callbacks?.onNoteSelected(note.title)
        }

    }

    private inner class NoteAdapter(var noteList: List<Note>) : RecyclerView.Adapter<NoteHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_note,
                parent, false)
            return NoteHolder(view)
        }

        override fun onBindViewHolder(holder: NoteHolder, position: Int) {
            val current = noteList[position]
            holder.bind(current)
        }

        override fun getItemCount(): Int {
            return noteList.size
        }

    }
}