package com.example.test

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test.databinding.FragmentAllNoteBinding
import java.util.Date

class AllNoteFragment : Fragment() {
    private val binding by lazy { FragmentAllNoteBinding.inflate(layoutInflater) }
    private val mainActivity by lazy { activity as MainActivity }
    val note = mutableListOf<Note>()
    private var check = true
    lateinit var noteAdd: Note

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        addNote()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addNote() {
        val adapter = NoteAdapter(note)

        if (::noteAdd.isInitialized && check) {
            check = false
            note.add(noteAdd)
            adapter.notifyDataSetChanged()
        }

        binding.managePost.adapter = adapter
        binding.managePost.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }
}