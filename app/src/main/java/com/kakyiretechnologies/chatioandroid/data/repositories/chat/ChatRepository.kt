package com.kakyiretechnologies.chatioandroid.data.repositories.chat


import com.kakyiretechnologies.chatioandroid.data.model.request.MessageModelRequest
import com.kakyiretechnologies.chatioandroid.data.model.response.*
import com.kakyiretechnologies.chatioandroid.utils.DataState
import kotlinx.coroutines.flow.Flow


/**
 * @author Kakyire
 * Created by Daniel Frimpong on October 28, 2022.
 * https://github.com/kakyire
 */
interface ChatRepository {

    fun sendMessage(messageModelRequest: MessageModelRequest): Flow<DataState<ApiResponse<MessageResponse>>>
    fun getMessages(chatId: String): Flow<DataState<ApiResponse<MessagesListResponse>>>
    fun getChats(): Flow<DataState<ApiResponse<ChatListResponse>>>

}