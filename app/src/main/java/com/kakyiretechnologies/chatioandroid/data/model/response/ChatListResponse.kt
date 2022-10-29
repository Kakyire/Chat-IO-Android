package com.kakyiretechnologies.chatioandroid.data.model.response


import androidx.annotation.Keep

@Keep
data class ChatListResponse(
    val chats: List<Chat>,
    val count: Int
)