package com.kakyiretechnologies.chatioandroid.ui.chat

import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.gson.Gson
import com.kakyiretechnologies.chatioandroid.R
import com.kakyiretechnologies.chatioandroid.data.api.socketio.SocketIOUtils
import com.kakyiretechnologies.chatioandroid.data.model.request.MessageModelRequest
import com.kakyiretechnologies.chatioandroid.data.model.response.MessagesListResponse
import com.kakyiretechnologies.chatioandroid.databinding.FragmentChatDetailsBinding
import com.kakyiretechnologies.chatioandroid.extensions.observeLiveData
import com.kakyiretechnologies.chatioandroid.extensions.observeLiveDataEvent
import com.kakyiretechnologies.chatioandroid.extensions.toast
import com.kakyiretechnologies.chatioandroid.preferences.Keys.USER_ID
import com.kakyiretechnologies.chatioandroid.preferences.PreferenceManager
import com.kakyiretechnologies.chatioandroid.ui.chat.adapters.MessagesListAdapter
import com.kakyiretechnologies.chatioandroid.utils.RECEIVE_MESSAGE
import com.kakyiretechnologies.chatioandroid.utils.SEND_MESSAGE
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject


/**
 * @author Kakyire
 * Created by Daniel Frimpong on October 24, 2022.
 * https://github.com/kakyire
 */
private const val TAG = "ChatDetails"

@AndroidEntryPoint
class ChatDetailsFragment : Fragment(R.layout.fragment_chat_details) {

    private lateinit var binding: FragmentChatDetailsBinding

    private val args by navArgs<ChatDetailsFragmentArgs>()

    private val chatViewModel by viewModels<ChatViewModel>()

    private var chatId: String? = null

    @Inject
    lateinit var preferenceManager: PreferenceManager

    @Inject
    lateinit var socketIOUtils: SocketIOUtils

    @Inject
    lateinit var messagesListAdapter: MessagesListAdapter

    private val currentUserId by lazy {
        preferenceManager.getString(USER_ID)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentChatDetailsBinding.bind(view)
        observeViewModelCallbacks()
        chatId = args.chatId
        setupRecyclerView()
        clickListeners()
        onTextChangeListener()
        observeViewModelCallbacks()


//        socketIOUtils.onEventReceive(RECEIVE_MESSAGE,MessagesListResponse::class.java){
//            Timber.tag(TAG).d("receivedMessage: $it")
//
//        }
        socketIOUtils.onEventReceive(RECEIVE_MESSAGE,MessagesListResponse::class.java){
            messagesListAdapter.submitList(it.messages)
             
              Timber.tag(TAG).d("messages: ${Gson().toJson(it.messages)}}")
              
        }
      }

    private fun onTextChangeListener() = with(binding) {
        edtMessage.doAfterTextChanged { btnSend.isEnabled = !it.isNullOrEmpty() }
    }

    private fun clickListeners() = with(binding) {
        btnSend.setOnClickListener {
            sendMessage()
        }
    }


    private fun setupRecyclerView() {
        binding.rvMessages.adapter = messagesListAdapter
        messagesListAdapter.userId = currentUserId
    }

    private fun sendMessage() = with(binding) {
        val message = edtMessage.text.toString()

        if (message.isEmpty()) {
            toast("You can't send empty message")
            return@with
        }

        val messageModelRequest = MessageModelRequest(
            text = message,
            receiverId = args.receiverId
        )

        socketIOUtils.sendEvent(SEND_MESSAGE,messageModelRequest)
        chatViewModel.sendMessage(messageModelRequest)
        edtMessage.setText("")
    }

    private fun observeViewModelCallbacks() = with(chatViewModel) {
        getMessages()
        observeLiveDataEvent(messages) {
            messagesListAdapter.apply {
                submitList(it.messages)
                userId = currentUserId
            }

        }
    }

    private fun getMessages() = with(chatViewModel) {
        chatId?.let { getMessages(it) }
    }
}