package com.kakyiretechnologies.chatioandroid.ui.chat.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.kakyiretechnologies.chatioandroid.data.model.response.Message
import com.kakyiretechnologies.chatioandroid.databinding.MessageItemBinding
import com.kakyiretechnologies.chatioandroid.databinding.MessagesListBinding
import timber.log.Timber
import javax.inject.Inject


/**
 * @author Kakyire
 * Created by Daniel Frimpong on October 28, 2022.
 * https://github.com/kakyire
 */
private const val TAG = "MessagesListAdapter"

class MessagesListAdapter @Inject constructor() :
    ListAdapter<Message, MessagesListAdapter.MessagesListViewHolder>(DIFF_UTIL) {

    var userId: String? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessagesListViewHolder {
        val binding = MessagesListBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MessagesListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MessagesListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MessagesListViewHolder(private val binding: MessagesListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(message: Message) = with(binding) {

            Timber.tag(TAG).d("messages:${Gson().toJson(message)}")

            if (userId != null) {

                if (message.sender.id == userId) {
                    senderMessage.setMessage(message)
                } else {
                    receiverMessage.setMessage(message)
                }
            }

        }

        private fun MessageItemBinding.setMessage(message: Message) {
            root.isVisible = true
            tvMessage.text = message.text
            tvUsername.text = message.sender.username
        }
    }

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<Message>() {
            override fun areItemsTheSame(oldItem: Message, newItem: Message): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Message, newItem: Message): Boolean {
                return oldItem == newItem
            }
        }
    }
}