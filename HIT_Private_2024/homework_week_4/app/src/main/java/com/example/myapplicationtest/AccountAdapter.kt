package com.example.myapplicationtest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationtest.databinding.ActivityAccountItemBinding

class AccountAdapter(
    private val accounts : List<Account>
): RecyclerView.Adapter<AccountAdapter.AccountViewHolder>() {

    inner class AccountViewHolder(val itemBinding: ActivityAccountItemBinding) : RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        return AccountViewHolder(ActivityAccountItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount() = accounts.size

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        holder.itemBinding.userName.text = accounts[position].userName
        holder.itemBinding.password.text = accounts[position].password
    }
}