package com.deazzle.modules.userdatamodule.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.deazzle.R
import com.deazzle.databinding.RecyclerViewProfileBinding
import com.deazzle.roomdatabase.entities.ObjResult
import com.deazzle.roomdatabase.entities.UsersData

class UsersAdapter : RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {

    private var profiles: List<UsersData>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UsersViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.recycler_view_profile,
            parent,
            false
        )
    )

    override fun getItemCount() = profiles?.size ?: 0

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        profiles?.let {
            holder.binding.result = it[position]
            holder.binding.executePendingBindings()
        }
    }

    fun setProfiles(profiles: List<UsersData>) {
        this.profiles = profiles
        notifyDataSetChanged()
    }

    inner class UsersViewHolder(val binding: RecyclerViewProfileBinding) :
        RecyclerView.ViewHolder(binding.root)

}