package com.example.myapplicationtest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplicationtest.databinding.ActivityListViewBinding

class ListViewActivity() : AppCompatActivity() {
    companion object {
        private val listAccount = mutableListOf<Account>()
    }

    private val binding by lazy { ActivityListViewBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        addEvent()
    }

    private fun addEvent() {

        val accountAdapter = AccountAdapter(accounts = listAccount)
        val bundle = intent.getBundleExtra("account")

        bundle?.getString("userName")
            ?.let { Account(userName = it, password = bundle.getString("password")!!) }
            ?.let { listAccount.add(it) }

        binding.recyclerView.adapter = accountAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
}