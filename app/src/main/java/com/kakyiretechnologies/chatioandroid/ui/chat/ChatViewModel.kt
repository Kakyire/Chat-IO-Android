package com.kakyiretechnologies.chatioandroid.ui.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kakyiretechnologies.chatioandroid.data.model.request.MessageModelRequest
import com.kakyiretechnologies.chatioandroid.data.model.response.ApiResponse
import com.kakyiretechnologies.chatioandroid.data.model.response.ChatListResponse
import com.kakyiretechnologies.chatioandroid.data.model.response.MessageResponse
import com.kakyiretechnologies.chatioandroid.data.model.response.MessagesListResponse
import com.kakyiretechnologies.chatioandroid.data.repositories.chat.ChatRepository
import com.kakyiretechnologies.chatioandroid.extensions.emitEventFlowResults
import com.kakyiretechnologies.chatioandroid.extensions.emitFlowResults
import com.kakyiretechnologies.chatioandroid.utils.DataState
import com.kakyiretechnologies.chatioandroid.utils.livedata.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


/**
 * @author Kakyire
 * Created by Daniel Frimpong on October 28, 2022.
 * https://github.com/kakyire
 */
@HiltViewModel
class ChatViewModel @Inject constructor(private val repository:ChatRepository) : ViewModel() {


    private val _sentMessage = MutableLiveData<DataState<ApiResponse<MessageResponse>>>()
    val sentMessage: LiveData<DataState<ApiResponse<MessageResponse>>> = _sentMessage


    private val _messages = MutableLiveData<Event<DataState<ApiResponse<MessagesListResponse>>>>()
    val messages: LiveData<Event<DataState<ApiResponse<MessagesListResponse>>>> = _messages


    private val _chats = MutableLiveData<DataState<ApiResponse<ChatListResponse>>>()
    val chats: LiveData<DataState<ApiResponse<ChatListResponse>>> = _chats

    fun sendMessage (messageModelRequest: MessageModelRequest)=emitFlowResults(_sentMessage){
        repository.sendMessage(messageModelRequest)
    }

    fun getMessages(chatId:String)=emitEventFlowResults(_messages){repository.getMessages(chatId)}

    fun getChats()=emitFlowResults(_chats){repository.getChats()}

}