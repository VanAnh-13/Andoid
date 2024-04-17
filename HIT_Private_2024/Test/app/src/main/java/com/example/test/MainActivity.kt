package com.example.test

import android.opengl.Visibility
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test.databinding.ActivityMainBinding
import java.util.Date

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        handleEvent()
    }

    private fun handleEvent() {
        addNote()
    }

    private fun addNote() {
        val note = generateSampleData(size = 5)

        binding.managePost.adapter = NoteAdapter(note)
        binding.managePost.visibility = View.VISIBLE
        binding.managePost.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    private fun generateSampleData(size: Int): List<Note> {
        val list = mutableListOf<Note>()
        for (i in 0 until size) {
            val title = "Title ${i + 1}"
            val content =
                "Content for post $i. Lorem ipsum dolor sit amet"
            val createdAt = Date(System.currentTimeMillis() - (i * 1000 * 60 * 60 * 24).toLong())
            val isFavorite = i % 2 == 0
            list.add(Note(title, content, createdAt, isFavorite))
        }
        return list
    }
}