package com.example.appdetailfree.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.example.appdetailfree.activities.MainActivity
import com.example.appdetailfree.databinding.FragmentSpendingBinding

class SpendingFragment : Fragment() {
    private val binding by lazy { FragmentSpendingBinding.inflate(layoutInflater) }
    private val mainActivity by lazy { activity as MainActivity }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        handleEvent()
    }

    private fun handleEvent() {
        getCategoryValue()
    }

    private fun getCategoryValue() {
        getCategory(binding.linerEating, binding.tvEating.text.toString())
        getCategory(binding.linerSpending, binding.tvSpendingIcon.text.toString())
        getCategory(binding.linerClothes, binding.tvClothes.text.toString())
        getCategory(binding.linerCollaborate, binding.tvCollaborate.text.toString())
        getCategory(binding.linerMedicine, binding.tvMedicine.text.toString())
        getCategory(binding.linerStudy, binding.tvStudy.text.toString())
        getCategory(binding.linerTransport, binding.tvTransport.text.toString())
        getCategory(binding.linerContact, binding.tvContact.text.toString())
        getCategory(binding.linerHome, binding.tvHome.text.toString())
    }

    private fun getCategory(liner: LinearLayout, categoryChoose: String) {
        liner.setOnClickListener {
            mainActivity.binding.edtDetailCategories.setText(categoryChoose)
        }
    }
}