package com.example.week5

import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.example.week5.databinding.NewFeedsLayoutBinding

class NewFeedsDataAdapter(
    private val newFeedsData: List<NewFeedsData>
) : RecyclerView.Adapter<NewFeedsDataAdapter.NewFeedsViewHolder>() {
    inner class NewFeedsViewHolder(val itemBinding: NewFeedsLayoutBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        NewFeedsViewHolder(NewFeedsLayoutBinding.inflate(LayoutInflater.from(parent.context)))

    override fun getItemCount() = newFeedsData.size

    override fun onBindViewHolder(holder: NewFeedsViewHolder, position: Int) {
        holder.itemBinding.apply {
            Glide.with(root.context)
                .load(newFeedsData[position].avt)
                .transform(CircleCrop())
                .into(avt)

            Glide.with(root.context)
                .load(newFeedsData[position].avt)
                .transform(CircleCrop())
                .into(avtComment)

            Glide.with(root.context)
                .load(newFeedsData[position].content)
                .into(content)

            nameProfile.text = newFeedsData[position].nameProfile
            nameProfileComment.text = newFeedsData[position].nameProfile
            sumOfLike.text = newFeedsData[position].sumOfLike
            comment.text = newFeedsData[position].comment
            sumOfComment.text = newFeedsData[position].sumOfComment
        }
    }
}