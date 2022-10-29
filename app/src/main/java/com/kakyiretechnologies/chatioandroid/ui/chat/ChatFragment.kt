package com.kakyiretechnologies.chatioandroid.ui.chat

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.gson.Gson
import com.kakyiretechnologies.chatioandroid.R
import com.kakyiretechnologies.chatioandroid.data.model.response.Chat
import com.kakyiretechnologies.chatioandroid.databinding.FragmentChatBinding
import com.kakyiretechnologies.chatioandroid.extensions.createMenu
import com.kakyiretechnologies.chatioandroid.extensions.navigateToNextPage
import com.kakyiretechnologies.chatioandroid.extensions.observeLiveData
import com.kakyiretechnologies.chatioandroid.preferences.Keys.IS_USER_LOGGED_IN
import com.kakyiretechnologies.chatioandroid.preferences.Keys.USER_ID
import com.kakyiretechnologies.chatioandroid.preferences.PreferenceManager
import com.kakyiretechnologies.chatioandroid.ui.chat.adapters.ChatListAdapter
import com.kakyiretechnologies.chatioandroid.utils.OnItemClickListener
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject


/**
 * @author Kakyire
 * Created by Daniel Frimpong on October 24, 2022.
 * https://github.com/kakyire
 */

private const val TAG = "ChatFragment"

@AndroidEntryPoint
class ChatFragment : Fragment(R.layout.fragment_chat), OnItemClickListener {

    private lateinit var binding: FragmentChatBinding

    private val chatViewModel by viewModels<ChatViewModel>()

    @Inject
    lateinit var preferenceManager: PreferenceManager

    @Inject
    lateinit var chatListAdapter: ChatListAdapter

    private val currentUserId by lazy {
        preferenceManager.getString(USER_ID)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentChatBinding.bind(view)

        createOptionMenu()
        clickListeners()
        setupRecyclerView()
        observeViewModelCallbacks()
    }

    private fun observeViewModelCallbacks() = with(chatViewModel) {
        getChats()
        observeLiveData(chats) {

            chatListAdapter.apply {
                submitList(it.chats)
                userId = currentUserId
            }


            Timber.tag(TAG).d("Users ${Gson().toJson(it)}")
        }
    }

    private fun setupRecyclerView() = with(binding) {
        rvUsers.adapter = chatListAdapter
    }

    private fun createOptionMenu() {
        createMenu(R.menu.chat_menu) {
            if (it == R.id.menLogout) {
                preferenceManager.save(IS_USER_LOGGED_IN, false)
                navigateToNextPage(
                    ChatFragmentDirections
                        .actionChatFragmentToLoginFragment()
                )
            }
        }
    }

    private fun clickListeners() = with(binding) {
        btnNewChat.setOnClickListener {
            navigateToNextPage(
                ChatFragmentDirections
                    .actionChatFragmentToStartChatFragment()
            )
        }
    }

    override fun onItemClick(model: Any) {

        Timber.tag(TAG).d("$model")
        val chat = model as Chat
        val user = chat.participants.find { chat.id != currentUserId!! }!!

        navigateToNextPage(
            ChatFragmentDirections.actionChatFragmentToChatDetailsFragment(
                chatId = chat.id,
                username = user.username,
                receiverId = user.id
            )
        )

    }


}