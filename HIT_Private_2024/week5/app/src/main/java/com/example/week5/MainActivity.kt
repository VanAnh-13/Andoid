package com.example.week5

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.commit
import com.bumptech.glide.Glide
import com.example.week5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val profileFragment = ProfileFragment()
    private val homeFragment= HomeFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        enableEdgeToEdge()
        addEvent()
    }

    /**
     * All event in this activity:
     * 1. Make round avatar
     * 2. View home fragment
     * 3. View profile fragment
     *
     * @author Le Van Anh
     */
    private fun addEvent() {
        makeRoundAvatar()
        viewHomeFragment()
        viewProfileFragment()
    }

    /**
     * View profile fragment
     *
     * @author Le Van Anh
     */
    private fun viewProfileFragment() {
        binding.btnMe.setOnClickListener {
            supportFragmentManager.commit {
                replace(binding.fragmentContainerViewTag.id, profileFragment)
                setReorderingAllowed(true)
            }
        }
    }

    /**
     * View home fragment
     *
     * @author Le Van Anh
     */
    private fun viewHomeFragment() {
        supportFragmentManager.commit {
            add(binding.fragmentContainerViewTag.id, homeFragment)
            setReorderingAllowed(true)
        }

        binding.btnHome.setOnClickListener {
            supportFragmentManager.commit {
                replace(binding.fragmentContainerViewTag.id, homeFragment)
                setReorderingAllowed(true)
            }
        }
    }

    /**
     * Make round avatar
     *
     * @author Le Van Anh
     */
    private fun makeRoundAvatar() {
        Glide.with(this)
            .load("https://i.pinimg.com/736x/32/a8/aa/32a8aa27169162cd5f78a294ef2aae34.jpg")
            .circleCrop()
            .into(binding.btnMe)
    }
}