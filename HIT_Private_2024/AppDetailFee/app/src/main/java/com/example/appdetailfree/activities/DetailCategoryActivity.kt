package com.example.appdetailfree.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appdetailfree.adapter.DetailCategoryAdapter
import com.example.appdetailfree.databinding.ActivityDetailCategoryBinding
import com.example.appdetailfree.model.DetailCategoryItem

class DetailCategoryActivity : AppCompatActivity() {
    private val binding by lazy { ActivityDetailCategoryBinding.inflate(layoutInflater) }
    private val adapter by lazy { DetailCategoryAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        addressProblem()
    }

    private fun addressProblem() {
        setAdapter()
        setTotal()
    }

    private fun setTotal() {
        val label = intent.getStringExtra("label")!!
        val total = intent.getStringExtra("total")!!

        binding.tvLabelDetailCategory.text = label
        binding.tvTotalDetailCategory.text = total
    }

    private fun setAdapter() {
        val listItem = intent.getParcelableArrayListExtra<DetailCategoryItem>("myListDetail")!!
        adapter.oldList = listItem
        binding.rvDetailCategory.adapter = adapter
        binding.rvDetailCategory.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
}