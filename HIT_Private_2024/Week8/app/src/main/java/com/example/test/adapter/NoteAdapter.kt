package com.example.test.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R
import com.example.test.data.Note
import com.example.test.data.NoteDoomDatabase
import com.example.test.databinding.NoteItemBinding
import com.example.test.fragment.AllNoteFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteAdapter(
    private val yourNoteData: List<Note>
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    inner class NoteViewHolder(val itemBinding: NoteItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = NoteViewHolder(
        itemBinding = NoteItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun getItemCount() = yourNoteData.size

    @SuppressLint("SetTextI18n", "NotifyDataSetChanged")
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {

        holder.itemBinding.apply {
            tvTitle.text = yourNoteData[position].title
            tvCreate.text = "Create at: ${yourNoteData[position].createdDateFormatted}"
            tvContent.text = yourNoteData[position].content

            if (yourNoteData[position].isFavorite) {
                holder.itemBinding.btnLike.setImageResource(R.drawable.red_heart)
            } else {
                holder.itemBinding.btnLike.setImageResource(R.drawable.broken_heart)
            }
        }

        holder.itemBinding.btnLike.setOnClickListener {
            val noteDoomDatabase by lazy { NoteDoomDatabase.getDatabase(holder.itemView.context) }
            val listData = noteDoomDatabase.noteDAO().getAllNote()

            val isFavorite = !yourNoteData[position].isFavorite
            yourNoteData[position].isFavorite = isFavorite

            if (yourNoteData[position].isFavorite) {
                holder.itemBinding.btnLike.setImageResource(R.drawable.red_heart)
            } else {
                holder.itemBinding.btnLike.setImageResource(R.drawable.broken_heart)
            }

            CoroutineScope(Dispatchers.IO).launch {
                noteDoomDatabase.noteDAO().updateNote(
                    Note(
                        id = listData[position].id,
                        title = listData[position].title,
                        content = listData[position].content,
                        createAt = listData[position].createAt,
                        isFavorite = isFavorite
                    )
                )

            }
        }
    }
}