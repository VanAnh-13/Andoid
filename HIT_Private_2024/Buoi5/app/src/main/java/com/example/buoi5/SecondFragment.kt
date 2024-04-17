package com.example.buoi5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.buoi5.databinding.FragmentFirstBinding
import com.example.buoi5.databinding.FragmentSecondBinding

class SecondFragment : Fragment() {

    private val binding by lazy { FragmentSecondBinding.inflate(layoutInflater) }
    private val mainActivity by lazy { activity as MainActivity }

//    var name: String = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.submit.setOnClickListener {
//            name = binding.name.text.toString()
            binding.name.setText(mainActivity.fullName)
            Toast.makeText(requireContext(), "Successful", Toast.LENGTH_SHORT).show()
        }
    }
}