package com.example.appdetailfree.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.example.appdetailfree.activities.MainActivity
import com.example.appdetailfree.databinding.FragmentIncomeBinding

class IncomeFragment : Fragment() {
    private val binding by lazy { FragmentIncomeBinding.inflate(layoutInflater) }
    private val mainActivity by lazy { activity as MainActivity }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
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
        getCategory(binding.linerIncome, binding.tvIncome.text.toString())
        getCategory(binding.linerAllowance, binding.tvAllowance.text.toString())
        getCategory(binding.linerBonus, binding.tvBonus.text.toString())
        getCategory(binding.linerSecondaryIncome, binding.tvSecondaryIncome.text.toString())
        getCategory(binding.linerInvest, binding.tvInvest.text.toString())
        getCategory(binding.linerTemporaryIncome, binding.tvTemporaryIncome.text.toString())
    }

    private fun getCategory(liner: LinearLayout, categoryChoose: String) {
        liner.setOnClickListener {
            mainActivity.binding.edtDetailCategories.setText(categoryChoose)
        }
    }
}