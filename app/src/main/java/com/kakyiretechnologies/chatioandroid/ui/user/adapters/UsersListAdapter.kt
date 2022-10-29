package com.kakyiretechnologies.chatioandroid.ui.user.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kakyiretechnologies.chatioandroid.data.model.response.UserResponse
import com.kakyiretechnologies.chatioandroid.databinding.ChatListItemsBinding
import com.kakyiretechnologies.chatioandroid.utils.OnItemClickListener
import javax.inject.Inject


/**
 * @author Kakyire
 * Created by Daniel Frimpong on October 28, 2022.
 * https://github.com/kakyire
 */
class UsersListAdapter @Inject constructor(private val listener: OnItemClickListener) :
    ListAdapter<UserResponse, UsersListAdapter.UsersListViewHolder>(DIFF_UTIL) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersListViewHolder {
        val binding = ChatListItemsBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return UsersListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UsersListViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    inner class UsersListViewHolder(private val binding: ChatListItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(userResponse: UserResponse, listener: OnItemClickListener) = with(binding) {
            tvUsername.text = userResponse.username
            root.setOnClickListener { listener.onItemClick(userResponse) }

        }
    }

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<UserResponse>() {
            override fun areItemsTheSame(oldItem: UserResponse, newItem: UserResponse): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: UserResponse, newItem: UserResponse): Boolean {
                return oldItem == newItem
            }
        }
    }
}