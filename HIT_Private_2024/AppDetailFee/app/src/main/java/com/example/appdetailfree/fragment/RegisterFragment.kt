package com.example.appdetailfree.fragment

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.appdetailfree.R
import com.example.appdetailfree.activities.MainActivity
import com.example.appdetailfree.databinding.FragmentRegisterBinding
import com.example.appdetailfree.model.FireBaseCostService

class RegisterFragment : Fragment() {
    private val binding by lazy { FragmentRegisterBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        handleEvent()
    }

    private fun handleEvent() {
        hidePassword()
        createAccount()
    }

    private fun createAccount() {
        binding.btnRegister.setOnClickListener {
            val username = binding.edtUsername.text.toString()
            val password = binding.edtPassword.text.toString()

            if (username != "" && password != "") {
                FireBaseCostService.authentication.createUserWithEmailAndPassword(
                    username,
                    password
                )
                    .addOnSuccessListener {
                        Toast.makeText(requireContext(), "Success", Toast.LENGTH_LONG).show()
                        startActivity(Intent(requireContext(), MainActivity::class.java))
                    }
                    .addOnFailureListener {
                        Toast.makeText(requireContext(), "Error", Toast.LENGTH_LONG).show()
                    }
            } else {
                if (username == "" && password == "") {
                    Toast.makeText(
                        requireContext(),
                        "Please enter your user name and password !",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    Toast.makeText(
                        requireContext(),
                        "Please enter your user name or password !",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

        }
    }

    private fun hidePassword() {
        var isHide = true
        binding.btnHidePassword.setOnClickListener {
            isHide = !isHide

            if (isHide) {
                binding.btnHidePassword.setImageResource(R.drawable.icons8_eye)
                binding.edtPassword.transformationMethod =
                    HideReturnsTransformationMethod.getInstance();
            } else {
                binding.btnHidePassword.setImageResource(R.drawable.icons8_hide_password)
                binding.edtPassword.transformationMethod =
                    PasswordTransformationMethod.getInstance()
            }
        }
    }
}