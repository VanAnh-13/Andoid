package com.example.appdetailfree.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appdetailfree.databinding.ActivityLoginRegisterBinding
import com.example.appdetailfree.fragment.LogInFragment
import com.example.appdetailfree.fragment.RegisterFragment

class LoginRegisterActivity : AppCompatActivity() {
    private val binding by lazy { ActivityLoginRegisterBinding.inflate(layoutInflater) }
    private val logInFragment by lazy { LogInFragment() }
    private val registerFragment by lazy { RegisterFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        solveProblem()
    }

    private fun solveProblem() {
        replaceFragment()
    }

    private fun replaceFragment() {
        var changeLayout = false

        supportFragmentManager
            .beginTransaction()
            .add(binding.fmLoginRegister.id, logInFragment)
            .commit()

        binding.btnRegister.setOnClickListener {
            changeLayout = !changeLayout
            setFragment(changeLayout)
        }
    }

    private fun setFragment(change: Boolean) {
        if (change) {
            supportFragmentManager
                .beginTransaction()
                .replace(binding.fmLoginRegister.id, registerFragment)
                .setReorderingAllowed(true)
                .commit()
        } else {
            supportFragmentManager
                .beginTransaction()
                .replace(binding.fmLoginRegister.id, logInFragment)
                .setReorderingAllowed(true)
                .commit()
        }
    }


}