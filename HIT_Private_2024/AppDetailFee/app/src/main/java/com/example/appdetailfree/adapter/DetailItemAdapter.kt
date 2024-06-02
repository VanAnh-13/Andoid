package com.example.appdetailfree.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.appdetailfree.databinding.LayoutItemDetailBinding
import com.example.appdetailfree.model.DetailItem
import java.text.DecimalFormat

class DetailItemAdapter(
    private val itemClickListen: (liner: LinearLayout, label: String, cost: String) -> Unit
) : Adapter<DetailItemAdapter.DetailViewHolder>() {
    inner class DetailViewHolder(val binding: LayoutItemDetailBinding) : ViewHolder(binding.root)

    var oldList: MutableList<DetailItem>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = DetailViewHolder(
        binding = LayoutItemDetailBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun getItemCount() = oldList.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        val formatNumber = DecimalFormat("#.###")
        formatNumber.minimumFractionDigits = 3

        holder.binding.apply {
            oldList[position].img?.let { imgItemDetail.setImageResource(it) }
            tvLabelItemDetail.text = oldList[position].label
            tvCostItemDetail.text = "${formatNumber.format(oldList[position].total)} d"

            itemClickListen.invoke(
                linearItemDetail,
                oldList[position].label,
                tvCostItemDetail.text.toString()
            )
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<DetailItem>() {
        override fun areItemsTheSame(oldItem: DetailItem, newItem: DetailItem) =
            oldItem.label == newItem.label

        override fun areContentsTheSame(oldItem: DetailItem, newItem: DetailItem) =
            oldItem == newItem

    }

    private val differ = AsyncListDiffer(this, differCallback)
}