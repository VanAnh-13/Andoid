package com.example.test

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test.databinding.NoteBinding

class NoteAdapter(
    private val yourNoteData: List<Note>
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    inner class NoteViewHolder(val itemBinding: NoteBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = NoteViewHolder(
        NoteBinding.inflate(
            LayoutInflater.from(parent.context)
        )
    )

    override fun getItemCount() = yourNoteData.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.itemBinding.apply {
            tvTitle.text = yourNoteData[position].title
            tvCreate.text = "Create at: ${yourNoteData[position].createAt}"
            tvContent.text = yourNoteData[position].content
        }

        holder.itemBinding.btnLike.setOnClickListener {
            holder.itemBinding.btnLike.setImageResource(R.drawable.red_heart)
        }
    }
}