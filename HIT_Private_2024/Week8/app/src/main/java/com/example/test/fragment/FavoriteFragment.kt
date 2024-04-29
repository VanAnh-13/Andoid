package com.example.test.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test.activity.MainActivity
import com.example.test.adapter.NoteAdapter
import com.example.test.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment() {
    private val binding by lazy { FragmentFavoriteBinding.inflate(layoutInflater) }
    private val activityMainActivity by lazy { activity as MainActivity }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    )  = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lineFavorite.adapter = NoteAdapter(activityMainActivity.favoriteList)
        binding.lineFavorite.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }
}