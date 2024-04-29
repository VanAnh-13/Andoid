package com.example.test

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.test.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private val binding by lazy { ActivitySecondBinding.inflate(layoutInflater) }
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

    private lateinit var title: String
    private lateinit var content: String

    private fun handleEvent() {
        addNote()
    }

    private fun checkTitleContent(title: String, content: String): Boolean {
        when {
            title == "" && content == "" -> {
                Toast.makeText(this, "Please enter title and content", Toast.LENGTH_LONG).show()
                return false
            }

            title == "" -> {
                Toast.makeText(this, "Please enter title", Toast.LENGTH_LONG).show()
                return false
            }

            content == "" -> {
                Toast.makeText(this, "Please enter content", Toast.LENGTH_LONG).show()
                return false
            }

            else -> return true
        }
    }

    private fun addNote() {
        binding.btnAddANote.setOnClickListener {
            title = binding.edtTitle.text.toString()
            content = binding.edtContent.text.toString()

            if (checkTitleContent(title = title, content = content)) {
                onStop()
            } else {
                return@setOnClickListener
            }
        }
    }

    // gọi khi có 1 activity đè lên activity hiện tại
    override fun onStop() {
        super.onStop()

        val resultIntent = Intent().apply {
            putExtra("title", title)
            putExtra("content", content)
        }

        setResult(RESULT_OK, resultIntent)

        finish()
    }
}