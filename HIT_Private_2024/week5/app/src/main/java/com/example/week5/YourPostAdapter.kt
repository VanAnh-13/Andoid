package com.example.week5

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.week5.databinding.YourPostLayoutBinding

class YourPostAdapter(
    private val yourPostData: List<YourPost>
) : RecyclerView.Adapter<YourPostAdapter.YourPostViewHolder>() {
    inner class YourPostViewHolder(val itemBinding: YourPostLayoutBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): YourPostViewHolder {
        return YourPostViewHolder(YourPostLayoutBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount() = yourPostData.size

    override fun onBindViewHolder(holder: YourPostViewHolder, position: Int) {
        holder.itemBinding.apply {
            Glide.with(root.context)
                .load(yourPostData[position].image)
                .centerCrop()
                .into(post)
        }
    }
}