package com.kakyiretechnologies.chatioandroid.ui.chat

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.gson.Gson
import com.kakyiretechnologies.chatioandroid.R
import com.kakyiretechnologies.chatioandroid.data.api.socketio.SocketIOUtils
import com.kakyiretechnologies.chatioandroid.data.model.payload.JoinChatPayload
import com.kakyiretechnologies.chatioandroid.data.model.payload.OnlineUserPayload
import com.kakyiretechnologies.chatioandroid.data.model.response.Chat
import com.kakyiretechnologies.chatioandroid.data.model.response.ChatListResponse
import com.kakyiretechnologies.chatioandroid.databinding.FragmentChatBinding
import com.kakyiretechnologies.chatioandroid.extensions.createMenu
import com.kakyiretechnologies.chatioandroid.extensions.navigateToNextPage
import com.kakyiretechnologies.chatioandroid.extensions.observeLiveData
import com.kakyiretechnologies.chatioandroid.preferences.Keys.IS_USER_LOGGED_IN
import com.kakyiretechnologies.chatioandroid.preferences.Keys.USER_ID
import com.kakyiretechnologies.chatioandroid.preferences.PreferenceManager
import com.kakyiretechnologies.chatioandroid.ui.MainActivity
import com.kakyiretechnologies.chatioandroid.ui.chat.adapters.ChatListAdapter
import com.kakyiretechnologies.chatioandroid.utils.CHATS_EVENT
import com.kakyiretechnologies.chatioandroid.utils.JOIN_CHAT_EVENT
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

    @Inject
    lateinit var socketIOUtils: SocketIOUtils

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
        socketIOUtils.onEventReceive(CHATS_EVENT, this, ChatListResponse::class.java) { response ->
//            if (currentUserId != null) {
            val chats = response.chats.distinct()
            chatListAdapter.submitList(chats)
//            }
        }
    }

    private fun observeViewModelCallbacks() = with(chatViewModel) {
        getChats()
        observeLiveData(chats) {

            chatListAdapter.apply {
                submitList(it.chats)
            }


            Timber.tag(TAG).d("Users ${Gson().toJson(it)}")
        }
    }

    private fun setupRecyclerView() = with(binding) {
        rvUsers.adapter = chatListAdapter
        chatListAdapter.userId = currentUserId
    }

    private fun createOptionMenu() {
        createMenu(R.menu.chat_menu) {
            if (it == R.id.menLogout) {
                preferenceManager.save(IS_USER_LOGGED_IN, false)
                navigateToNextPage(
                    ChatFragmentDirections
                        .actionChatFragmentToLoginFragment()
                )
                socketIOUtils.disconnect()
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

    override fun onResume() {
        super.onResume()
        val onlineUserPayload = OnlineUserPayload(currentUserId!!)
        socketIOUtils.apply {



            if (!isConnected()) {
                connect()
                joinOnlineUsers(onlineUserPayload)
            }
        }
    }

    override fun onItemClick(model: Any) {

        val chat = model as Chat
        if (currentUserId != null) {
            val user = chat.participants.find { it.id != currentUserId }!!


            val joinChatPayload = JoinChatPayload(chat.id)
//            socketIOUtils.sendEvent(JOIN_CHAT_EVENT, joinChatPayload)

            navigateToNextPage(
                ChatFragmentDirections.actionChatFragmentToChatDetailsFragment(
                    chatId = chat.id,
                    username = user.username,
                    receiverId = user.id
                )
            )
        }
    }


}