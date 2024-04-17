package com.example.buoi5

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.commit
import com.bumptech.glide.Glide
import com.example.buoi5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    private val firstFragment = FirstFragment()
    private val secondFragment = SecondFragment()
    var fullName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnFragment1.setOnClickListener {
            supportFragmentManager.commit {
                replace(binding.fragmentContainer.id, firstFragment)
                setReorderingAllowed(true)
            }
        }

        binding.btnFragment2.setOnClickListener {
            supportFragmentManager.commit {
                replace(binding.fragmentContainer.id, secondFragment)
                setReorderingAllowed(true)
            }
        }

        binding.submitMain.setOnClickListener {
//           fullName = binding.name.text.toString()
            Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show()
        }

        val dialog = AlertDialog.Builder(this)
    }
}