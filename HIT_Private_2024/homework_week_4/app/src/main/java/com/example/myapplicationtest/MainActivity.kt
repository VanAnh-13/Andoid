package com.example.myapplicationtest

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplicationtest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        addEvent()
    }

    private fun addEvent() {
        binding.btnSignUp.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        binding.btnLogin.setOnClickListener {
            val intent = Intent(this, ListViewActivity::class.java)
            val bundle = Bundle()
            val userName = binding.userName.text.toString()
            val password = binding.password.text.toString()

            when {
                userName == "" && password == "" -> {
                    Toast.makeText(this, "Please enter username and password", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                userName == "" -> {
                    Toast.makeText(this, "Please enter username", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                password == "" -> {
                    Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                else -> {
                    bundle.putString("userName", userName)
                    bundle.putString("password", password)
                }
            }

            intent.putExtra("account", bundle)
            startActivity(intent)
        }
    }
}