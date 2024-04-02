package com.example.myapplicationtest

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplicationtest.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private val binding by lazy { ActivitySignUpBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.tvSignIn.setOnClickListener {
            finish()
        }

        addEvent()
    }

    @SuppressLint("SetTextI18n")
    private fun addEvent() {
        binding.btnLogin.setOnClickListener {
            val intent = Intent(this, ListViewActivity::class.java)
            val bundle = Bundle()
            val userName = binding.userName.text.toString()
            val password = binding.password.text.toString()
            val submitPassword = binding.submitPassword.text.toString()

            when {
                userName == "" && password == "" -> {
                    Toast.makeText(this, "Please enter username and password", Toast.LENGTH_SHORT)
                        .show()
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

                submitPassword == "" -> {
                    Toast.makeText(this, "Please enter submit password", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                !Regex(pattern = submitPassword).matches(password) -> {
                    Toast.makeText(this, "Your password does not match !", Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }

                !Regex(pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\W)(?=.*\\d).{8,}").matches(password) -> {
                    binding.tvIntroduce.text =
                        "Password must have least 1 lower character, 1 upper character, 1 special character, 1 number and password length must least 8 character !"
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