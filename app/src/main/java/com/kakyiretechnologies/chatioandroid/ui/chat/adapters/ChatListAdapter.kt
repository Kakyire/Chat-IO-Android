package com.kakyiretechnologies.chatioandroid.ui.chat.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kakyiretechnologies.chatioandroid.data.model.response.Chat
import com.kakyiretechnologies.chatioandroid.databinding.ChatListItemsBinding
import com.kakyiretechnologies.chatioandroid.utils.OnItemClickListener
import timber.log.Timber
import javax.inject.Inject


/**
 * @author Kakyire
 * Created by Daniel Frimpong on October 26, 2022.
 * https://github.com/kakyire
 */
class ChatListAdapter @Inject constructor(
    private val listener: OnItemClickListener,
) :
    ListAdapter<Chat, ChatListAdapter.ChatListViewHolder>(DIFF_UTIL) {
    var userId: String? = null

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


        fun bind(chat: Chat, listener: OnItemClickListener) = with(binding) {
            if (userId != null) {
                val user = chat.participants.find {
                    it.id != userId
                }

                  Timber.tag(TAG).d("username: ${user?.username}")

                tvUsername.text = user?.username
            }
            root.setOnClickListener { listener.onItemClick(chat) }

        }
    }

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<Chat>() {
            override fun areItemsTheSame(oldItem: Chat, newItem: Chat): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Chat, newItem: Chat): Boolean {
                return oldItem == newItem
            }
        }
    }
}

private const val TAG = "ChatListAdapter"