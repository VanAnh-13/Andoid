package com.example.appdetailfree.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.appdetailfree.databinding.LayoutItemDetailCategoryBinding
import com.example.appdetailfree.model.DetailCategoryItem

class DetailCategoryAdapter : Adapter<DetailCategoryAdapter.CategoryDetailViewHolder>() {
    inner class CategoryDetailViewHolder(val binding: LayoutItemDetailCategoryBinding) :
        RecyclerView.ViewHolder(binding.root)

    var oldList: MutableList<DetailCategoryItem>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CategoryDetailViewHolder(
        binding = LayoutItemDetailCategoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun getItemCount() = oldList.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CategoryDetailViewHolder, position: Int) {
        holder.binding.apply {
            tvLabelItemDetailCategory.text = oldList[position].label
            tvDateItemDetailCategory.text = oldList[position].date
            tvCostItemDetailCategory.text = oldList[position].cost
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<DetailCategoryItem>() {
        override fun areItemsTheSame(oldItem: DetailCategoryItem, newItem: DetailCategoryItem) =
            oldItem.label == newItem.label

        override fun areContentsTheSame(oldItem: DetailCategoryItem, newItem: DetailCategoryItem) =
            oldItem == newItem

    }

    private val differ = AsyncListDiffer(this, differCallback)
}