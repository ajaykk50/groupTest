package com.example.groupsapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.groupsapp.databinding.RowListBinding
import com.example.groupsapp.pojo.Group
import com.example.groupsapp.pojo.groupList

class GroupListAdapter(
    private val context: Context,
    private val originalData: groupList,
    private val onItemClick: (Group) -> Unit
) :
    RecyclerView.Adapter<GroupListAdapter.ViewHolder>() {

    private var groupData: groupList = originalData

    inner class ViewHolder(val binding: RowListBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            RowListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return groupData.result.groups.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var data = groupData.result.groups.get(position)
        Glide.with(context).load(data.group_photo).into(holder.binding.ivUserImage)
        holder.binding.tvUserName.setText(data.name)
        holder.binding.tvUserType.setText(data.user_status)
        holder.binding.tvUserMsg.setText(data.bio)
        holder.binding.tvGroupCount.setText(data.participant_count.toString())
        holder.binding.tvUnreadCount.setText(data.unread_count.toString())

        holder.binding.container.setOnClickListener {
            onItemClick(groupData.result.groups.get(position))
        }
    }
}