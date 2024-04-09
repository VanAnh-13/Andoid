package com.example.week5

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.week5.databinding.StoryLayoutBinding

class StoryAdapter(
    private val storyData: List<StoryData>
) : RecyclerView.Adapter<StoryAdapter.StoryViewHolder>() {
    inner class StoryViewHolder(val itemBinding: StoryLayoutBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        StoryViewHolder(StoryLayoutBinding.inflate(LayoutInflater.from(parent.context)))

    override fun getItemCount() = storyData.size

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        holder.itemBinding.apply {
            Glide.with(root.context)
                .load(storyData[position].image)
                .transform(CircleCrop())
                .into(imageView)

            textView.text = storyData[position].name
        }
    }
}