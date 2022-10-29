package com.kakyiretechnologies.chatioandroid.data.repositories.chat

import com.kakyiretechnologies.chatioandroid.data.api.ApiService
import com.kakyiretechnologies.chatioandroid.data.model.request.MessageModelRequest
import com.kakyiretechnologies.chatioandroid.data.model.response.*
import com.kakyiretechnologies.chatioandroid.utils.DataState
import com.kakyiretechnologies.chatioandroid.utils.makeNetworkCall
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 * @author Kakyire
 * Created by Daniel Frimpong on October 28, 2022.
 * https://github.com/kakyire
 */
class ChatRepositoryImpl @Inject constructor(private val apiService: ApiService) : ChatRepository {
    override fun sendMessage(messageModelRequest: MessageModelRequest): Flow<DataState<ApiResponse<MessageResponse>>> {
        return makeNetworkCall { apiService.sendMessage(messageModelRequest) }
    }

    override fun getMessages(chatId: String): Flow<DataState<ApiResponse<MessagesListResponse>>> {
        return makeNetworkCall { apiService.getMessages(chatId) }
    }

    override fun getChats(): Flow<DataState<ApiResponse<ChatListResponse>>> {
        return makeNetworkCall { apiService.getChats() }
    }
}