package com.example.test.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test.adapter.NoteAdapter
import com.example.test.data.Note
import com.example.test.databinding.FragmentAllNoteBinding

class AllNoteFragment : Fragment() {
    private val binding by lazy { FragmentAllNoteBinding.inflate(layoutInflater) }
    val note = mutableListOf<Note>()
    private val adapter = NoteAdapter(note)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        addNote()
    }

    private fun addNote() {
        binding.managePost.adapter = adapter
        binding.managePost.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    override fun onResume() {
        super.onResume()

        adapter.notifyItemChanged(note.size - 1)
    }
}