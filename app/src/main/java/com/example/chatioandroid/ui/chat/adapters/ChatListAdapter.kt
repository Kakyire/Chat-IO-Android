package com.example.chatioandroid.ui.chat.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.chatioandroid.data.model.response.UserResponse
import com.example.chatioandroid.databinding.ChatListItemsBinding
import com.example.chatioandroid.utils.OnItemClickListener
import javax.inject.Inject


/**
 * @author Kakyire
 * Created by Daniel Frimpong on October 26, 2022.
 * https://github.com/kakyire
 */
class ChatListAdapter @Inject constructor(private val listener: OnItemClickListener) :
    ListAdapter<UserResponse, ChatListAdapter.ChatListViewHolder>(DIFF_UTIL) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatListViewHolder {
        val binding = ChatListItemsBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ChatListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatListViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    inner class ChatListViewHolder(private val binding: ChatListItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(userResponse: UserResponse, listener: OnItemClickListener) = with(binding) {
            tvUsername.text = userResponse.username
            root.setOnClickListener { listener.onItemClick(userResponse) }

        }
    }

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<UserResponse>() {
            override fun areItemsTheSame(oldItem: UserResponse, newItem: UserResponse): Boolean {
                return oldItem.email == newItem.email
            }

            override fun areContentsTheSame(oldItem: UserResponse, newItem: UserResponse): Boolean {
                return oldItem == newItem
            }
        }
    }
}